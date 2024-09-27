package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

public class MockUserExpenses {

	public MockUserExpenses() {
		super();
	}

	public UserExpenses generateMockUserExpense(User user, Long seed) {
		Long id = seed;
		String name = "Expense" + seed.toString();
		double value = 1000 + seed;
		String category = "Category" + seed.toString();
		boolean isStaticValue = seed % 2 == 0;
		return new UserExpenses(id, name, value, category, isStaticValue, user);
	}

	public List<UserExpenses> generateMockUserExpenses(User user, Long seed, int size) {
		List<UserExpenses> expenses = new ArrayList<UserExpenses>();
		for (Long i = seed; i < seed + size; i++) {
			expenses.add(this.generateMockUserExpense(user, i));
		}
		return expenses;
	}

}
