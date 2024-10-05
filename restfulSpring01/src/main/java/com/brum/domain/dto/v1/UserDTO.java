package com.brum.domain.dto.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.brum.domain.dto.v2.UserDTOH;
import com.brum.domain.entities.Investment;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "fullName", "email", "password", "paymentOptions", "sheets", "investmenst",
		"savedExpenses" })
public class UserDTO {

	@JsonProperty("id")
	private Long key;
	private String fullName;
	private String email;
	private String password;
	private List<PaymentOptionDTO> paymentOptions;
	private List<SheetDTO> sheets;
	private List<InvestmentDTO> investments;
	private List<UserExpensesDTO> savedExpenses;

	public UserDTO() {
	}

	public UserDTO(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.paymentOptions = new ArrayList<PaymentOptionDTO>();
		this.sheets = new ArrayList<SheetDTO>();
		this.investments = new ArrayList<InvestmentDTO>();
		this.savedExpenses = new ArrayList<UserExpensesDTO>();
	}

	public UserDTO(User user) {
		this.key = user.getId();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOptionDTO>();
		this.sheets = new ArrayList<SheetDTO>();
		this.investments = new ArrayList<InvestmentDTO>();
		this.savedExpenses = new ArrayList<UserExpensesDTO>();

		if (user.getPaymentOptions() != null) {
			this.paymentOptions = user.getPaymentOptions().stream().map(PaymentOptionDTO::new)
					.collect(Collectors.toList());
		}
		if (user.getSheets() != null) {
			this.sheets = user.getSheets().stream().map(SheetDTO::new).collect(Collectors.toList());
		}

		if (user.getInvestments() != null) {
			this.investments = user.getInvestments().stream().map(InvestmentDTO::new).collect(Collectors.toList());
		}

		if (user.getSavedExpenses() != null) {
			this.savedExpenses = user.getSavedExpenses().stream().map(UserExpensesDTO::new)
					.collect(Collectors.toList());
		}
	}

	public UserDTO(UserDTOH user) {
		this.key = user.getKey();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.paymentOptions = new ArrayList<PaymentOptionDTO>();
		this.sheets = new ArrayList<SheetDTO>();
		this.investments = new ArrayList<InvestmentDTO>();
		this.savedExpenses = new ArrayList<UserExpensesDTO>();

		if (user.getPaymentOptions() != null) {
			this.paymentOptions = user.getPaymentOptions().stream().map(PaymentOptionDTO::new)
					.collect(Collectors.toList());
		}
		if (user.getSheets() != null) {
			this.sheets = user.getSheets().stream().map(SheetDTO::new).collect(Collectors.toList());
		}

		if (user.getInvestments() != null) {
			this.investments = user.getInvestments().stream().map(InvestmentDTO::new).collect(Collectors.toList());
		}

		if (user.getSavedExpenses() != null) {
			this.savedExpenses = user.getSavedExpenses().stream().map(UserExpensesDTO::new)
					.collect(Collectors.toList());
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
			user.setPaymentOptions(
					this.paymentOptions.stream().map(PaymentOptionDTO::dtoToEntity).collect(Collectors.toList()));
		}
		if (this.sheets != null) {
			user.setSheets(this.sheets.stream().map(SheetDTO::dtoToEntity).collect(Collectors.toList()));
		}

		if (this.investments != null) {
			user.setInvestments(this.investments.stream().map(InvestmentDTO::dtoToEntity).collect(Collectors.toList()));
		}

		if (this.savedExpenses != null) {
			user.setSavedExpenses(
					this.savedExpenses.stream().map(UserExpensesDTO::dtoToEntity).collect(Collectors.toList()));
		}

		return user;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
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

	public List<PaymentOptionDTO> getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(List<PaymentOptionDTO> paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public List<SheetDTO> getSheets() {
		return sheets;
	}

	public void setSheets(List<SheetDTO> sheets) {
		this.sheets = sheets;
	}

	public List<InvestmentDTO> getInvestments() {
		return investments;
	}

	public void setInvestments(List<InvestmentDTO> investments) {
		this.investments = investments;
	}

	public List<UserExpensesDTO> getSavedExpenses() {
		return savedExpenses;
	}

	public void setSavedExpenses(List<UserExpensesDTO> savedExpenses) {
		this.savedExpenses = savedExpenses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, investments, key, password, paymentOptions, savedExpenses, sheets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(investments, other.investments) && key == other.key
				&& Objects.equals(password, other.password) && Objects.equals(paymentOptions, other.paymentOptions)
				&& Objects.equals(savedExpenses, other.savedExpenses) && Objects.equals(sheets, other.sheets);
	}

	@Override
	public String toString() {
		return "UserDTO [key=" + key + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", paymentOptions=" + paymentOptions + ", sheets=" + sheets + ", investments=" + investments
				+ ", savedExpenses=" + savedExpenses + "]";
	}

}
