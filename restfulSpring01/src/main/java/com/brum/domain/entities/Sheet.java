package com.brum.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sheet_t")
public class Sheet {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SheetExpenses> expenses;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Sheet() {
	}

	public Sheet(Long id, String name, List<SheetExpenses> expenses, User user) {
		super();
		this.id = id;
		this.name = name;
		this.expenses = expenses;
		this.user = user;
	}

	public Sheet(Long id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
		this.expenses = new ArrayList<SheetExpenses>();
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenses, id, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sheet other = (Sheet) obj;
		return Objects.equals(expenses, other.expenses) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Sheet [id=" + id + ", name=" + name + ", expenses=" + expenses + ", userId=" + user.getId() + "]";
	}

}
