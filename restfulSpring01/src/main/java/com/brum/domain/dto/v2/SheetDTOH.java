package com.brum.domain.dto.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.brum.domain.dto.v1.SheetDTO;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "user", "expenses"})
public class SheetDTOH extends RepresentationModel<SheetDTOH> {

	@JsonProperty("id")
	private Long key;
	private String name;
	private List<SheetExpenses> expenses;
	private User user;

	public SheetDTOH() {
	}

	public SheetDTOH(String name, List<SheetExpenses> expenses, User user) {
		this.name = name;
		this.expenses = expenses;
		this.user = user;
	}
	
	public SheetDTOH(String name,  User user) {
		this.name = name;
		this.user = user;
		this.expenses = new ArrayList<SheetExpenses>();
	}

	public SheetDTOH(Sheet sheet) {
		this.key = sheet.getId();
		this.name = sheet.getName();
		this.user = sheet.getUser();
		this.expenses = new ArrayList<SheetExpenses>();

		if (sheet.getExpenses() != null) {
			this.expenses = sheet.getExpenses();
		}
	}

	public SheetDTOH(SheetDTO sheet) {
		this.key = sheet.getKey();
		this.name = sheet.getName();
		this.user = sheet.getUser();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sheet dtoToEntity() {
		Sheet sheet = new Sheet();
		sheet.setId(this.key);
		sheet.setName(this.name);
		sheet.setUser(this.user);
		if (this.expenses != null) {
			sheet.setExpenses(this.expenses);
		} else {
			sheet.setExpenses(new ArrayList<SheetExpenses>());
		}
		return sheet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenses, key, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetDTOH other = (SheetDTOH) obj;
		return Objects.equals(expenses, other.expenses) && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "SheetDTO [key=" + key + ", name=" + name + ", expenses=" + expenses + ", user=" + user + "]";
	}
}
