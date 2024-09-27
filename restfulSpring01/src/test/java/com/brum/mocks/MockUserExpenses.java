package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

public class MockUserExpenses {

	private final Random random;

	public MockUserExpenses(long seed) {
		this.random = new Random(seed);
	}

	public UserExpenses generateMockUserExpense(User user) {
		Long id = random.nextLong();
		String name = this.generateRandomString("Expense", random.nextInt(1000));
		double value = random.nextDouble() * 1000;
		String category = this.generateRandomString("Category", random.nextInt(10));
		boolean isStaticValue = random.nextBoolean();
		return new UserExpenses(id, name, value, category, isStaticValue, user);
	}

	public List<UserExpenses> generateMockUserExpenses(User user) {
		int count = random.nextInt(5);
		List<UserExpenses> expenses = new ArrayList<UserExpenses>();
		for (int i = 0; i < count; i++) {
			expenses.add(this.generateMockUserExpense(user));
		}
		return expenses;
	}

	private String generateRandomString(String prefix, int length) {
		StringBuilder sb = new StringBuilder(prefix);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + random.nextInt(26)));
		}
		return sb.toString();
	}

}
