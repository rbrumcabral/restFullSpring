package com.brum.domain.dto.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.brum.domain.dto.v2.SheetDTOH;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "user", "expenses" })
public class SheetDTO {

	@JsonProperty("id")
	private Long key;
	private String name;
	private List<SheetExpenses> expenses;
	private Long userId;

	public SheetDTO() {
	}

	public SheetDTO(String name, List<SheetExpenses> expenses, Long user) {
		this.name = name;
		this.expenses = expenses;
		this.userId = user;
	}

	public SheetDTO(String name, Long user) {
		this.name = name;
		this.userId = user;
		this.expenses = new ArrayList<SheetExpenses>();
	}

	public SheetDTO(Sheet sheet) {
		this.key = sheet.getId();
		this.name = sheet.getName();
		this.userId = sheet.getUser().getId();
		this.expenses = new ArrayList<SheetExpenses>();

		if (sheet.getExpenses() != null) {
			this.expenses = sheet.getExpenses();
		}
	}

	public SheetDTO(SheetDTOH sheet) {
		this.key = sheet.getKey();
		this.name = sheet.getName();
		this.userId = sheet.getUserId();
		this.expenses = new ArrayList<SheetExpenses>();

		if (sheet.getExpenses() != null) {
			this.expenses = sheet.getExpenses();
		}
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SheetExpenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<SheetExpenses> expenses) {
		this.expenses = expenses;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long user) {
		this.userId = user;
	}

	public Sheet dtoToEntity() {
		Sheet sheet = new Sheet();
		sheet.setId(this.key);
		sheet.setName(this.name);
		sheet.getUser().setId((this.userId));
		if (this.expenses != null) {
			sheet.setExpenses(this.expenses);
		} else {
			sheet.setExpenses(new ArrayList<SheetExpenses>());
		}
		return sheet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenses, key, name, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetDTO other = (SheetDTO) obj;
		return Objects.equals(expenses, other.expenses) && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "SheetDTO [key=" + key + ", name=" + name + ", expenses=" + expenses + ", user=" + userId + "]";
	}

}
