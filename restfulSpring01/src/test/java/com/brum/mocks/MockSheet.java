package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.brum.domain.entities.User;

public class MockSheet {

	private final Random random;

	public MockSheet(long seed) {
		this.random = new Random(seed);
	}

	public Sheet generateMockSheet(User user) {
		Long id = random.nextLong();
		String name = this.generateRandomString("Sheet", random.nextInt(1000));

		List<SheetExpenses> expenses = this.generateMockSheetExpenses();

		return new Sheet(id, name, expenses, user);
	}

	public List<Sheet> generateMockSheets(User user) {
		int count = random.nextInt(5);
		List<Sheet> sheets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			sheets.add(generateMockSheet(user));
		}
		return sheets;
	}

	private List<SheetExpenses> generateMockSheetExpenses() {
		int count = random.nextInt(5);
		List<SheetExpenses> expenses = new ArrayList<SheetExpenses>();
		for (int i = 0; i < count; i++) {
			expenses.add(new SheetExpenses());
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
