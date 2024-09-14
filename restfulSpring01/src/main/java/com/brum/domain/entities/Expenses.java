package com.brum.domain.entities;

import lombok.Data;

@Data
public class Expenses {
	
	private long id;

	private String name;
	
	private double value;
	
	private boolean isStaticValue;
	
}
