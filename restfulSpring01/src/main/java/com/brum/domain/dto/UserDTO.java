package com.brum.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;

public class UserDTO {

	private long id;
	private String fullName;
	private String email;
	private String password;
	private List<PaymentOption> paymentOptions;

	public UserDTO() {
	}

	public UserDTO(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = new ArrayList<PaymentOption>();
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOption>();
		
		if (user.getPaymentOptions() != null) {
			this.paymentOptions = new ArrayList<>(user.getPaymentOptions());
		}
	}

	public User dtoToEntity() {
		User user = new User();
		user.setEmail(this.email);
		user.setFullName(this.fullName);
		user.setId(this.id);
		user.setPassword(this.password);
		user.setPaymentOptions(new ArrayList<PaymentOption>());
		
		if (this.paymentOptions != null) {
			user.setPaymentOptions(new ArrayList<>(this.paymentOptions));
		}
		return user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	@Override
	public String toString() {
		return super.toString();
	}

}
