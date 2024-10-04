package com.brum.domain.dto.v2;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.brum.domain.dto.v1.SheetExpensesDTO;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "category", "value", "isStaticValue", "sheet"})
public class SheetExpensesDTOH extends RepresentationModel<SheetExpensesDTOH>{

	@JsonProperty("id")
	protected long key;
	protected String name;
	protected double value;
	protected String category;
	protected boolean isStaticValue;
	private Sheet sheet;
	
	public SheetExpensesDTOH() {
	}

	public SheetExpensesDTOH(SheetExpensesDTOH sheetExpenses) {
		this.key = sheetExpenses.getKey();
		this.name = sheetExpenses.getName();
		this.value = sheetExpenses.getValue();
		this.category = sheetExpenses.getCategory();
		this.isStaticValue = sheetExpenses.isStaticValue();
		this.sheet = sheetExpenses.getSheet();
	}

	public SheetExpensesDTOH(String name, double value, String category, boolean isStaticValue, Sheet sheet) {
		this.name = name;
		this.value = value;
		this.category = category;
		this.isStaticValue = isStaticValue;
		this.sheet = sheet;
	}
	
	public SheetExpensesDTOH(SheetExpensesDTO sheetExpenses) {
		this.key = sheetExpenses.getKey();
		this.name = sheetExpenses.getName();
		this.value = sheetExpenses.getValue();
		this.category = sheetExpenses.getCategory();
		this.isStaticValue = sheetExpenses.isStaticValue();
		this.sheet = sheetExpenses.getSheet();
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

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public SheetExpenses dtoToEntity() {
		SheetExpenses entity = new SheetExpenses();
		entity.setId(this.key);
		entity.setName(this.name);
		entity.setSheet(this.sheet);
		entity.setStaticValue(this.isStaticValue);
		entity.setCategory(this.category);
		entity.setValue(this.value);
		return entity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(category, isStaticValue, key, name, sheet, value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetExpensesDTOH other = (SheetExpensesDTOH) obj;
		return Objects.equals(category, other.category) && isStaticValue == other.isStaticValue && key == other.key
				&& Objects.equals(name, other.name) && Objects.equals(sheet, other.sheet)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "SheetExpensesDTOH [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category
				+ ", isStaticValue=" + isStaticValue + ", sheet=" + sheet + "]";
	}	
	

}
