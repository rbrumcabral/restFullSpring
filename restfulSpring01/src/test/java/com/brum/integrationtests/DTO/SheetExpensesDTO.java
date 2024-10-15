package com.brum.integrationtests.DTO;

import java.util.Objects;

import com.brum.domain.dto.v2.SheetExpensesDTOH;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "category", "value", "isStaticValue", "sheet" })
public class SheetExpensesDTO {

	@JsonProperty("id")
	protected Long key;
	protected String name;
	protected double value;
	protected String category;
	protected boolean isStaticValue;
	private Long sheetId;

	public SheetExpensesDTO() {
	}

	public SheetExpensesDTO(SheetExpenses entity) {
		this.key = entity.getId();
		this.name = entity.getName();
		this.value = entity.getValue();
		this.category = entity.getCategory();
		this.isStaticValue = entity.isStaticValue();
		this.sheetId = entity.getSheet().getId();
	}

	public SheetExpensesDTO(SheetExpensesDTO sheetExpenses) {
		this.key = sheetExpenses.getKey();
		this.name = sheetExpenses.getName();
		this.value = sheetExpenses.getValue();
		this.category = sheetExpenses.getCategory();
		this.isStaticValue = sheetExpenses.isStaticValue();
		this.sheetId = sheetExpenses.getSheetId();
	}

	public SheetExpensesDTO(SheetExpensesDTOH sheetExpenses) {
		this.key = sheetExpenses.getKey();
		this.name = sheetExpenses.getName();
		this.value = sheetExpenses.getValue();
		this.category = sheetExpenses.getCategory();
		this.isStaticValue = sheetExpenses.isStaticValue();
		this.sheetId = sheetExpenses.getSheetId();
	}

	public SheetExpensesDTO(String name, double value, String category, boolean isStaticValue, Long sheetId) {
		this.name = name;
		this.value = value;
		this.category = category;
		this.isStaticValue = isStaticValue;
		this.sheetId = sheetId;
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

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheet(Long sheetId) {
		this.sheetId = sheetId;
	}

	public SheetExpenses dtoToEntity() {
		SheetExpenses entity = new SheetExpenses();
		entity.setId(this.key);
		entity.setName(this.name);
		entity.setSheet(new Sheet());
		entity.getSheet().setId(this.sheetId);
		entity.setStaticValue(this.isStaticValue);
		entity.setCategory(this.category);
		entity.setValue(this.value);
		return entity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, isStaticValue, key, name, sheetId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetExpensesDTO other = (SheetExpensesDTO) obj;
		return Objects.equals(category, other.category) && isStaticValue == other.isStaticValue && key == other.key
				&& Objects.equals(name, other.name) && Objects.equals(sheetId, other.sheetId)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "SheetExpensesDTO [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category
				+ ", isStaticValue=" + isStaticValue + ", sheet=" + sheetId + "]";
	}

}
