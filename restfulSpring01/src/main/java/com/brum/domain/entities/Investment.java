package com.brum.domain.entities;

public class Investment {

	private static final long serialVersionUID = 1L;

	private String name;

	private double value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
