package com.brum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brum.repositories.JwtUserRepository;

@Service
public class JwtUserService implements UserDetailsService {

	@Autowired
	JwtUserRepository repository;

	public JwtUserService(JwtUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var jwtUser = this.repository.findByUsername(username);
		if (jwtUser != null) {
			return jwtUser;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}
}
