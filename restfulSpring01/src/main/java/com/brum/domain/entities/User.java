package com.brum.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_t")
public class User {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PaymentOption> paymentOptions;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sheet> sheets;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Investment> investments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserExpenses> savedExpenses;

	public User() {
	}

	public User(long id, String fullName, String email, String password, List<PaymentOption> paymentOptions,
			List<Sheet> sheets, List<Investment> investments, List<UserExpenses> savedExpenses) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = paymentOptions;
		this.sheets = sheets;
		this.investments = investments;
		this.savedExpenses = savedExpenses;
	}

	public User(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = new ArrayList<PaymentOption>();
		this.sheets = new ArrayList<Sheet>();
		this.investments = new ArrayList<Investment>();
		this.savedExpenses = new ArrayList<UserExpenses>();
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

	public List<Sheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	public List<UserExpenses> getSavedExpenses() {
		return savedExpenses;
	}

	public void setSavedExpenses(List<UserExpenses> savedExpenses) {
		this.savedExpenses = savedExpenses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, id, investments, password, paymentOptions, savedExpenses, sheets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(investments, other.investments) && Objects.equals(password, other.password)
				&& Objects.equals(paymentOptions, other.paymentOptions)
				&& Objects.equals(savedExpenses, other.savedExpenses) && Objects.equals(sheets, other.sheets);
	}

}
