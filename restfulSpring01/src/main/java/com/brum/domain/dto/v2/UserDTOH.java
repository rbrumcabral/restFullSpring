package com.brum.domain.dto.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.entities.Investment;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "fullName", "email", "password", "paymentOptions" })
public class UserDTOH extends RepresentationModel<UserDTOH> {

	@JsonProperty("id")
	private long key;
	private String fullName;
	private String email;
	private String password;
	private List<PaymentOption> paymentOptions;
	private List<Sheet> sheets;
	private List<Investment> investments;
	private List<UserExpenses> savedExpenses;

	public UserDTOH() {
	}

	public UserDTOH(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = new ArrayList<PaymentOption>();
		this.sheets = new ArrayList<Sheet>();
		this.investments = new ArrayList<Investment>();
		this.savedExpenses = new ArrayList<UserExpenses>();
	}

	public UserDTOH(User user) {
		this.key = user.getId();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOption>();
		this.sheets = new ArrayList<Sheet>();
		this.investments = new ArrayList<Investment>();
		this.savedExpenses = new ArrayList<UserExpenses>();

		if (user.getPaymentOptions() != null) {
			this.paymentOptions = new ArrayList<>(user.getPaymentOptions());
		}
		if (user.getSheets() != null) {
			this.sheets = new ArrayList<>(user.getSheets());
		}

		if (user.getInvestments() != null) {
			this.investments = new ArrayList<>(user.getInvestments());
		}

		if (user.getSavedExpenses() != null) {
			this.savedExpenses = new ArrayList<>(user.getSavedExpenses());
		}
	}

	public UserDTOH(UserDTO user) {
		this.key = user.getKey();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOption>();
		this.sheets = new ArrayList<Sheet>();
		this.investments = new ArrayList<Investment>();
		this.savedExpenses = new ArrayList<UserExpenses>();

		if (user.getPaymentOptions() != null) {
			this.paymentOptions = new ArrayList<>(user.getPaymentOptions());
		}

		if (user.getSheets() != null) {
			this.sheets = new ArrayList<>(user.getSheets());
		}

		if (user.getInvestments() != null) {
			this.investments = new ArrayList<>(user.getInvestments());
		}

		if (user.getSavedExpenses() != null) {
			this.savedExpenses = new ArrayList<>(user.getSavedExpenses());
		}
	}

	public User dtoToEntity() {
		User user = new User();
		user.setEmail(this.email);
		user.setFullName(this.fullName);
		user.setId(this.key);
		user.setPassword(this.password);
		user.setPaymentOptions(new ArrayList<PaymentOption>());
		user.setSavedExpenses(new ArrayList<UserExpenses>());
		user.setInvestments(new ArrayList<Investment>());
		user.setSheets(new ArrayList<Sheet>());

		if (this.paymentOptions != null) {
			user.setPaymentOptions(new ArrayList<>(this.paymentOptions));
		}

		if (this.savedExpenses != null) {
			user.setSavedExpenses(new ArrayList<>(this.savedExpenses));
		}

		if (this.investments != null) {
			user.setInvestments(new ArrayList<>(this.investments));
		}

		if (this.sheets != null) {
			user.setSheets(new ArrayList<>(this.sheets));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(email, fullName, investments, key, password, paymentOptions, savedExpenses, sheets);
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
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(investments, other.investments) && key == other.key
				&& Objects.equals(password, other.password) && Objects.equals(paymentOptions, other.paymentOptions)
				&& Objects.equals(savedExpenses, other.savedExpenses) && Objects.equals(sheets, other.sheets);
	}

	@Override
	public String toString() {
		return "UserDTOH [key=" + key + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", paymentOptions=" + paymentOptions + ", sheets=" + sheets + ", investments=" + investments
				+ ", savedExpenses=" + savedExpenses + "]";
	}

}
