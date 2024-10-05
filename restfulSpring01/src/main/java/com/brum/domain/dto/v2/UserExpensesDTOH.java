package com.brum.domain.dto.v2;

import java.util.Objects;

import com.brum.domain.dto.v1.UserExpensesDTO;
import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "value", "category", "isStaticValue", "user" })
public class UserExpensesDTOH {
	@JsonProperty("id")
	private long key;
	private String name;
	private double value;
	private String category;
	private boolean isStaticValue;
	private long userId;

	public UserExpensesDTOH() {
	}

	public UserExpensesDTOH(String name, double value, String category, boolean isStaticValue, long userId) {
		this.name = name;
		this.value = value;
		this.category = category;
		this.isStaticValue = isStaticValue;
		this.userId = userId;
	}

	public UserExpensesDTOH(UserExpenses userExpenses) {
		this.key = userExpenses.getId();
		this.name = userExpenses.getName();
		this.value = userExpenses.getValue();
		this.category = userExpenses.getCategory();
		this.isStaticValue = userExpenses.isStaticValue();
		this.userId = userExpenses.getUser().getId();
	}

	public UserExpensesDTOH(UserExpensesDTO userExpenses) {
		this.key = userExpenses.getKey();
		this.name = userExpenses.getName();
		this.value = userExpenses.getValue();
		this.category = userExpenses.getCategory();
		this.isStaticValue = userExpenses.isStaticValue();
		this.userId = userExpenses.getUserId();
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isStaticValue() {
		return isStaticValue;
	}

	public void setStaticValue(boolean isStaticValue) {
		this.isStaticValue = isStaticValue;
	}

	public long getUserId() {
		return userId;
	}

	public void setUser(long userId) {
		this.userId = userId;
	}

	public UserExpenses dtoToEntity() {
		UserExpenses entity = new UserExpenses();
		entity.setId(this.key);
		entity.setName(this.name);
		entity.setStaticValue(this.isStaticValue);
		entity.setCategory(this.category);
		entity.setValue(this.value);
		entity.setUser(new User());
		entity.getUser().setId(this.userId);

		return entity;
	}


	@Override
	public int hashCode() {
		return Objects.hash(category, isStaticValue, key, name, userId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserExpensesDTOH other = (UserExpensesDTOH) obj;
		return Objects.equals(category, other.category) && isStaticValue == other.isStaticValue && key == other.key
				&& Objects.equals(name, other.name) && userId == other.userId
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "UserExpensesDTO [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category
				+ ", isStaticValue=" + isStaticValue + ", user=" + userId + "]";
	}
}
