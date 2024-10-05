package com.brum.domain.dto.v1;

import java.util.Objects;

import com.brum.domain.dto.v2.InvestmentDTOH;
import com.brum.domain.entities.Investment;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "value", "category", "user" })
public class InvestmentDTO {

	@JsonProperty("id")
	private long key;
	private String name;
	private double value;
	private String category;
	private long userId;

	public InvestmentDTO() {
	}

	public InvestmentDTO(String name, double value, String category, long userId) {
		this.name = name;
		this.value = value;
		this.category = category;
		this.userId = userId;
	}

	public InvestmentDTO(Investment investment) {
		this.key = investment.getId();
		this.name = investment.getName();
		this.value = investment.getValue();
		this.category = investment.getCategory();
		this.userId = investment.getUser().getId();
	}

	public InvestmentDTO(InvestmentDTOH investment) {
		this.key = investment.getKey();
		this.name = investment.getName();
		this.value = investment.getValue();
		this.category = investment.getCategory();
		this.userId = investment.getUserId();
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

	public long getUserId() {
		return userId;
	}

	public void setUser(long userId) {
		this.userId = userId;
	}

	public Investment dtoToEntity() {
		Investment entity = new Investment();
		entity.setId(this.key);
		entity.setName(this.name);
		entity.setUser(new User());
		entity.getUser().setId(this.userId);
		entity.setCategory(this.category);
		entity.setValue(this.value);

		return entity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, key, name, userId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentDTO other = (InvestmentDTO) obj;
		return Objects.equals(category, other.category) && key == other.key && Objects.equals(name, other.name)
				&& userId == other.userId && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "InvestmentDTO [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category
				+ ", userId=" + userId + "]";
	}

}
