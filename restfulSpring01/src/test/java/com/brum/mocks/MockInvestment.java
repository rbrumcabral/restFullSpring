package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.Investment;
import com.brum.domain.entities.User;

public class MockInvestment {

	private final Random random;

	public MockInvestment(long seed) {
		this.random = new Random(seed);
	}

	public Investment generateMockInvestment(User user) {
		Long id = random.nextLong(100);
		String name = this.generateRandomString("Investment", random.nextInt(1000));
		double value = random.nextDouble() * 10000;
		String category = this.generateRandomString("Category", random.nextInt(10));

		return new Investment(id, name, value, category, user);
	}

	public List<Investment> generateMockInvestments(User user) {
		int count = random.nextInt(5);
		List<Investment> investments = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			investments.add(this.generateMockInvestment(user));
		}
		return investments;
	}

	private String generateRandomString(String prefix, int length) {
		StringBuilder sb = new StringBuilder(prefix);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + random.nextInt(26)));
		}
		return sb.toString();
	}
}
