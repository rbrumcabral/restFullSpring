package com.brum.mocks;

import java.util.ArrayList;
import java.util.List;

import com.brum.domain.entities.Investment;
import com.brum.domain.entities.User;

public class MockInvestment {

	public MockInvestment() {
		super();
	}

	public Investment generateMockInvestment(User user, Long seed) {
		Long id = seed;
		String name = "Investment" + seed.toString();
		double value = 1000L * seed;
		String category = "Category" + seed.toString();

		return new Investment(id, name, value, category, user);
	}

	public List<Investment> generateMockInvestments(User user, Long seed, int size) {
		List<Investment> investments = new ArrayList<>();
		for (Long i = seed; i < seed + size; i++) {
			investments.add(this.generateMockInvestment(user, i));
		}
		return investments;
	}

}
