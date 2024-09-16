package com.brum.domain.entities;

public class Expenses {

	private static final long serialVersionUID = 1L;

	private String name;

	private double value;

	private boolean isStaticValue;

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

	public boolean isStaticValue() {
		return isStaticValue;
	}

	public void setStaticValue(boolean isStaticValue) {
		this.isStaticValue = isStaticValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
