package com.brum.domain.entities;

import lombok.Data;

@Data
public class User {
	
	private long id;

	private String username;
	
	private String email;
}
