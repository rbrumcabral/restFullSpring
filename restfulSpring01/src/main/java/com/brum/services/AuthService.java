package com.brum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brum.domain.dto.v1.security.AccountCredentialsDTO;
import com.brum.domain.dto.v1.security.TokenDTO;
import com.brum.repositories.JwtUserRepository;
import com.brum.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserRepository repository;

	public ResponseEntity<TokenDTO> signin(AccountCredentialsDTO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = repository.findByUsername(username);
			var tokenReponse = new TokenDTO();

			if (user == null) {
				throw new UsernameNotFoundException("Username: " + username + " not found!");
			}

			tokenReponse = jwtTokenProvider.createAccessToken(username, user.getRoles());

			return ResponseEntity.ok(tokenReponse);
		} catch (Exception ex) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}

	}

	public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {

		var user = repository.findByUsername(username);
		var tokenReponse = new TokenDTO();

		if (user == null) {
			throw new UsernameNotFoundException("Username: " + username + " not found!");
		}

		tokenReponse = jwtTokenProvider.createAccessToken(username, user.getRoles());

		return ResponseEntity.ok(tokenReponse);

	}

}
