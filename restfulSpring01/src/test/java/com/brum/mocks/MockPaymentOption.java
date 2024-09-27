package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;

public class MockPaymentOption {

	private final Random random;

	public MockPaymentOption(long seed) {
		this.random = new Random(seed);
	}

	public PaymentOption generateMockPaymentOption(User user) {
		Long id = random.nextLong(100);
		String name = this.generateRandomString("PaymentOption", random.nextInt(1000));
		double creditLimit = random.nextDouble() * 5000;

		return new PaymentOption(id, name, creditLimit, user);
	}

	public List<PaymentOption> generateMockPaymentOptions(User user) {
		int count = random.nextInt(5);
		List<PaymentOption> paymentOptions = new ArrayList<PaymentOption>();
		for (int i = 0; i < count; i++) {
			paymentOptions.add(this.generateMockPaymentOption(user));
		}
		return paymentOptions;
	}

	private String generateRandomString(String prefix, int length) {
		StringBuilder sb = new StringBuilder(prefix);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + random.nextInt(26)));
		}
		return sb.toString();
	}

}
