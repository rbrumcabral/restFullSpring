package com.brum.domain.dto.v2;

import java.util.Objects;

import com.brum.domain.dto.v1.PaymentOptionDTO;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "creditLimit", "user" })
public class PaymentOptionDTOH {
	@JsonProperty("id")
	private long key;
	private String name;
	private double creditLimit;
	private long userId;

	public PaymentOptionDTOH() {
	}

	public PaymentOptionDTOH(String name, double creditLimit, long userId) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.userId = userId;
	}

	public PaymentOptionDTOH(PaymentOption paymentOption) {
		this.key = paymentOption.getId();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUser().getId();
	}

	public PaymentOptionDTOH(PaymentOptionDTO paymentOption) {
		this.key = paymentOption.getKey();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUserId();
	}

	public PaymentOptionDTOH(PaymentOptionDTOH paymentOption) {
		this.key = paymentOption.getKey();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUserId();
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long user) {
		this.userId = user;
	}

	public PaymentOption dtoToEntity() {
		PaymentOption entity = new PaymentOption();
		entity.setId(this.key);
		entity.setName(this.name);
		entity.setCreditLimit(this.creditLimit);
		entity.setUser(new User());
		entity.getUser().setId(this.userId);
		return entity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditLimit, key, name, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentOptionDTOH other = (PaymentOptionDTOH) obj;
		return Double.compare(other.creditLimit, creditLimit) == 0 && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "PaymentOptionDTO [key=" + key + ", name=" + name + ", creditLimit=" + creditLimit + ", user=" + userId
				+ "]";
	}
}
