package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Investment;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

public class MockUser {

	MockSheet mockSheet;

	MockPaymentOption mockPaymentOption;

	MockUserExpenses mockUserExpenses;

	MockSheetExpenses mockSheetExpenses;

	MockInvestment mockInvestment;

	public MockUser() {
		this.mockPaymentOption = new MockPaymentOption();
		this.mockSheet = new MockSheet();
		this.mockUserExpenses = new MockUserExpenses();
		this.mockSheetExpenses = new MockSheetExpenses();
		this.mockInvestment = new MockInvestment();
	}

	public User generateMockUser(Long seed) {
		long id = seed;
		String fullName = "User" + seed.toString();
		String email = "user" + seed.toString() + "@example.com";
		String password = "pass" + seed.toString();

		List<PaymentOption> paymentOptions = new ArrayList<>();
		List<Sheet> sheets = new ArrayList<>();
		List<Investment> investments = new ArrayList<>();
		List<UserExpenses> savedExpenses = new ArrayList<>();

		User user = new User(id, fullName, email, password, paymentOptions, sheets, investments, savedExpenses);
		return user;
	}

	public User generateMockUserComplete(Long seed) {
		User user = this.generateMockUser(seed);
		List<PaymentOption> paymentOptions = this.mockPaymentOption.generateMockPaymentOptions(user, 1L, 1);
		List<Sheet> sheets = this.mockSheet.generateMockSheets(user, 1L, 1);
		List<Investment> investments = this.mockInvestment.generateMockInvestments(user, 1L, 1);
		List<UserExpenses> userExpenses = this.mockUserExpenses.generateMockUserExpenses(user, 1L, 1);
		user.setInvestments(investments);
		user.setPaymentOptions(paymentOptions);
		user.setSavedExpenses(userExpenses);
		user.setSheets(sheets);
		return user;
	}

}
