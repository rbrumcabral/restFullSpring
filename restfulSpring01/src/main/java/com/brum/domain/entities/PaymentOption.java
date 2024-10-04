package com.brum.domain.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_option_t")
public class PaymentOption {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column(name = "credit_limit")
	private double creditLimit;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public PaymentOption() {
	}

	public PaymentOption(Long id, String name, double creditLimit, User user) {
		this.id = id;
		this.name = name;
		this.creditLimit = creditLimit;
		this.user = user;
	}

	public PaymentOption(String name, double limit) {
		this.name = name;
		this.creditLimit = limit;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditLimit, id, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentOption other = (PaymentOption) obj;
		return Double.doubleToLongBits(creditLimit) == Double.doubleToLongBits(other.creditLimit)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "PaymentOption [id=" + id + ", name=" + name + ", creditLimit=" + creditLimit + ", user=" + user + "]";
	}

}
