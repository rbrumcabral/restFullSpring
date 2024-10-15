package com.brum.integrationtests.DTO;

import java.util.Objects;

import com.brum.domain.dto.v2.PaymentOptionDTOH;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "creditLimit", "user" })
public class PaymentOptionDTO {

	@JsonProperty("id")
	private Long key;
	private String name;
	private double creditLimit;
	private Long userId;

	public PaymentOptionDTO() {
	}

	public PaymentOptionDTO(String name, double creditLimit, Long userId) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.userId = userId;
	}

	public PaymentOptionDTO(PaymentOption paymentOption) {
		this.key = paymentOption.getId();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUser().getId();
	}

	public PaymentOptionDTO(PaymentOptionDTO paymentOption) {
		this.key = paymentOption.getKey();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUserId();
	}

	public PaymentOptionDTO(PaymentOptionDTOH paymentOption) {
		this.key = paymentOption.getKey();
		this.name = paymentOption.getName();
		this.creditLimit = paymentOption.getCreditLimit();
		this.userId = paymentOption.getUserId();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		PaymentOptionDTO other = (PaymentOptionDTO) obj;
		return Double.compare(other.creditLimit, creditLimit) == 0 && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "PaymentOptionDTO [key=" + key + ", name=" + name + ", creditLimit=" + creditLimit + ", user=" + userId
				+ "]";
	}
}
