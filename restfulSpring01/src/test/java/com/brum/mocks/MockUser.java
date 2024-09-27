package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.Investment;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

public class MockUser {

	private final Random random;

	public MockUser(long seed) {
		this.random = new Random(seed);
	}

	public User generateMockUser() {
		long id = random.nextInt(100);
		String fullName = this.generateRandomString("User", 5);
		String email = this.generateRandomString("user", 5) + "@example.com";
		String password = this.generateRandomString("pass", 8);

		List<PaymentOption> paymentOptions = new ArrayList<>();
		List<Sheet> sheets = new ArrayList<>();
		List<Investment> investments = new ArrayList<>();
		List<UserExpenses> savedExpenses = new ArrayList<>();

		User user = new User(id, fullName, email, password, paymentOptions, sheets, investments, savedExpenses);
		return user;
	}

	public User generateMockUserComplete(List<PaymentOption> paymentOptions, List<Sheet> sheets,
			List<Investment> investments, List<UserExpenses> savedExpenses) {
		User user = this.generateMockUser();
		user.setPaymentOptions(paymentOptions);
		user.setSavedExpenses(savedExpenses);
		user.setInvestments(investments);
		user.setSheets(sheets);
		return user;
	}

	private String generateRandomString(String prefix, int length) {
		StringBuilder sb = new StringBuilder(prefix);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + random.nextInt(26)));
		}
		return sb.toString();
	}

}
