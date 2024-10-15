package com.brum.exceptions.entities;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{
	
	private static final long serialVersionUID = 1L;

	public InvalidJwtAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidJwtAuthenticationException(String message) {
		super(message);
	}

}
