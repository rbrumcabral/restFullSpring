package com.brum.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.brum.domain.dto.v1.security.TokenDTO;
import com.brum.exceptions.entities.InvalidJwtAuthenticationException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expired-length:3600000}")
	private Long validityInMilliseconds = 3600000L;

	@Autowired
	private UserDetailsService userDetailsService;

	Algorithm algorithm = null;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	
	private JwtTokenProvider(){
	}

	public TokenDTO createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + this.validityInMilliseconds);
		var accessToken = this.getAccessToken(username, roles, now, validity);
		var refreshToken = this.getRefreshToken(username, roles, now);
		return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
	}

	private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validity).withSubject(username)
				.withIssuer(issuerUrl).sign(algorithm).strip();
	}

	private String getRefreshToken(String username, List<String> roles, Date now) {
		Date validityRefreshToken = new Date(now.getTime() + this.validityInMilliseconds);
		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validityRefreshToken)
				.withSubject(username).sign(algorithm).strip();
	}

	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodedToken(String token) {
		var algorithmAux = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(algorithmAux).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			if (decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
		}

	}

}
