package com.brum.domain.entities;

import java.util.List;

public class Period {

	private static final long serialVersionUID = 1L;

	private String name;

	private List<Expenses> expenses;

	public Period() {
	}

	public Period(String name, List<Expenses> expenses) {

		this.name = name;
		this.expenses = expenses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
