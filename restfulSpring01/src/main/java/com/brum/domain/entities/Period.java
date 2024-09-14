package com.brum.domain.entities;

import java.util.List;

import lombok.Data;

@Data
public class Period {
	
	private long id;

	private String name;
	
	private List<Expenses> expenses;
	
}
