package com.brum.exceptions.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNull extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredObjectIsNull(String message, Throwable cause) {
		super(message, cause);
	}

	public RequiredObjectIsNull(String message) {
		super(message);
	}

}
