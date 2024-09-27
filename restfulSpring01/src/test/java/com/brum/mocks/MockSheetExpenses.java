package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;

public class MockSheetExpenses {

	private final Random random;

	public MockSheetExpenses(long seed) {
		this.random = new Random(seed);
	}

	public SheetExpenses generateMockSheetExpense(Sheet sheet) {
		Long id = random.nextLong();
		String name = this.generateRandomString("Expense", random.nextInt(1000));
		double value = random.nextDouble() * 1000;
		String category = this.generateRandomString("Category", random.nextInt(10));
		boolean isStaticValue = random.nextBoolean();

		return new SheetExpenses(id, name, value, category, isStaticValue, sheet);
	}

	public List<SheetExpenses> generateMockSheetExpenses(Sheet sheet) {
		int count = random.nextInt(5);
		List<SheetExpenses> expenses = new ArrayList<SheetExpenses>();
		for (int i = 0; i < count; i++) {
			expenses.add(this.generateMockSheetExpense(sheet));
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
