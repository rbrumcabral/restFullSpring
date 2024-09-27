package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.brum.domain.entities.User;

public class MockSheet {

	MockSheetExpenses mockSheetExpenses;

	public MockSheet() {
		this.mockSheetExpenses = new MockSheetExpenses();
	}

	public Sheet generateMockSheet(User user, Long seed) {
		Long id = seed;
		String name = "Sheet" + seed.toString();
		List<SheetExpenses> expensesAux = new ArrayList<SheetExpenses>();
		var auxSheet = new Sheet(id, name, expensesAux, user);
		List<SheetExpenses> expenses = mockSheetExpenses.generateMockSheetExpenses(auxSheet, 1L, 1);
		return new Sheet(id, name, expenses, user);
	}

	public List<Sheet> generateMockSheets(User user, Long seed, int size) {
		List<Sheet> sheets = new ArrayList<>();
		for (Long i = seed; i < seed + size; i++) {
			sheets.add(generateMockSheet(user, seed));
		}
		return sheets;
	}

}
