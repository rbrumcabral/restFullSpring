package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;

public class MockPaymentOption {

	public MockPaymentOption() {
		super();
	}

	public PaymentOption generateMockPaymentOption(User user, Long seed) {
		Long id = seed;
		String name = "PaymentOption" + seed.toString();
		double creditLimit = 1000 + seed;

		return new PaymentOption(id, name, creditLimit, user);
	}

	public List<PaymentOption> generateMockPaymentOptions(User user, Long seed, int size) {
		List<PaymentOption> paymentOptions = new ArrayList<PaymentOption>();
		for (Long i = seed; i < seed + size; i++) {
			paymentOptions.add(this.generateMockPaymentOption(user, seed));
		}
		return paymentOptions;
	}

}
