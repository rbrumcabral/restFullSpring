package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Investment;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

public class MockUser {

	public MockUser() {
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

	public User generateMockUserComplete(List<PaymentOption> paymentOptions, List<Sheet> sheets,
			List<Investment> investments, List<UserExpenses> savedExpenses, Long seed) {
		User user = this.generateMockUser(seed);
		user.setPaymentOptions(paymentOptions);
		user.setSavedExpenses(savedExpenses);
		user.setInvestments(investments);
		user.setSheets(sheets);
		return user;
	}

}
