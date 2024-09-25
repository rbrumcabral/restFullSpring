package com.brum.domain.dto.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "fullName", "email", "password", "paymentOptions"})
public class UserDTOH extends RepresentationModel<UserDTOH> {

	@JsonProperty("id")
	private long key;
	private String fullName;
	private String email;
	private String password;
	private List<PaymentOption> paymentOptions;

	public UserDTOH() {
	}

	public UserDTOH(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = new ArrayList<PaymentOption>();
	}

	public UserDTOH(User user) {
		this.key = user.getId();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOption>();
		
		if (user.getPaymentOptions() != null) {
			this.paymentOptions = new ArrayList<>(user.getPaymentOptions());
		}
	}
	
	public UserDTOH(UserDTO user) {
		this.key = user.getKey();
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
		user.setId(this.key);
		user.setPassword(this.password);
		user.setPaymentOptions(new ArrayList<PaymentOption>());
		
		if (this.paymentOptions != null) {
			user.setPaymentOptions(new ArrayList<>(this.paymentOptions));
		}
		return user;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, fullName, key, password, paymentOptions);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTOH other = (UserDTOH) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && key == other.key
				&& Objects.equals(password, other.password) && Objects.equals(paymentOptions, other.paymentOptions);
	}
	
}
