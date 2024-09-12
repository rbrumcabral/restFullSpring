package com.brum.domain.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User {
	
	private long id;

	private String username;
	
	private String email;
}
