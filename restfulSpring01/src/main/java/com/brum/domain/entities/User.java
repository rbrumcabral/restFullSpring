package com.brum.domain.entities;

import java.util.List;

public class User {

	private static final long serialVersionUID = 1L;

	private String fullName;

	private String username;

	private String email;

	private String password;

	private List<PaymentOption> paymentOptions;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PaymentOption> getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(List<PaymentOption> paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
