package com.brum.domain.entities;

import java.util.Objects;

import com.brum.domain.entities.interfaces.BaseExpenses;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sheet_expenses_t")
public class SheetExpenses extends BaseExpenses {

	@ManyToOne
	@JoinColumn(name = "sheet_id", nullable = false)
	private Sheet sheet;

	public SheetExpenses() {
		super();
	}

	public SheetExpenses(String name, double value, boolean isStaticValue) {
		super();
		this.name = name;
		this.value = value;
		this.isStaticValue = isStaticValue;
	}
	
	public SheetExpenses(long id, String name, double value, String category, boolean isStaticValue, Sheet sheet) {
		super(id, name, value, category, isStaticValue);
		this.sheet = sheet;
	}

	public Sheet getSheet() {
		return this.sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(sheet);
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
		SheetExpenses other = (SheetExpenses) obj;
		return Objects.equals(sheet, other.sheet);
	}

	@Override
	public String toString() {
		return "SheetExpenses [sheet=" + sheet + ", id=" + id + ", name=" + name + ", value=" + value + ", category="
				+ category + ", isStaticValue=" + isStaticValue + "]";
	}

}
