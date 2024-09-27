package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.brum.domain.entities.User;

public class MockSheet {

	public MockSheet() {
		super();
	}

	public Sheet generateMockSheet(User user, List<SheetExpenses> expenses, Long seed) {
		Long id = seed;
		String name = "Sheet" + seed.toString();
		return new Sheet(id, name, expenses, user);
	}

	public List<Sheet> generateMockSheets(User user, List<SheetExpenses> expenses, Long seed, int size) {
		List<Sheet> sheets = new ArrayList<>();
		for (Long i = seed; i < seed + size; i++) {
			sheets.add(generateMockSheet(user, expenses, seed));
		}
		return sheets;
	}

}
