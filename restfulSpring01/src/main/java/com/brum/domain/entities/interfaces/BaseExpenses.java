package com.brum.domain.entities.interfaces;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseExpenses {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false)
	protected String name;

	@Column(nullable = false)
	protected double value;

	@Column
	protected String category;

	@Column(name = "is_static_value", nullable = false)
	protected boolean isStaticValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isStaticValue() {
		return isStaticValue;
	}

	public void setStaticValue(boolean isStaticValue) {
		this.isStaticValue = isStaticValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isStaticValue, name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseExpenses other = (BaseExpenses) obj;
		return id == other.id && isStaticValue == other.isStaticValue && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

}
