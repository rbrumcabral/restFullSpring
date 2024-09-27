package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;

public class MockSheetExpenses {

	public MockSheetExpenses() {
		super();
	}

	public SheetExpenses generateMockSheetExpense(Sheet sheet, Long seed) {
		Long id = seed;
		String name = "Expense" + seed.toString();
		double value = 1000 + seed;
		String category = "Category" + seed.toString();
		boolean isStaticValue = seed % 2 == 0;

		return new SheetExpenses(id, name, value, category, isStaticValue, sheet);
	}

	public List<SheetExpenses> generateMockSheetExpenses(Sheet sheet, Long seed, int size) {
		List<SheetExpenses> expenses = new ArrayList<SheetExpenses>();
		for (Long i = seed; i < seed + size; i++) {
			expenses.add(this.generateMockSheetExpense(sheet, seed));
		}
		return expenses;
	}

}
