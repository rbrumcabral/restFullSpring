package com.brum.domain.entities;

import java.util.Objects;

import com.brum.domain.entities.interfaces.BaseExpenses;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_expenses_t")
public class UserExpenses extends BaseExpenses {

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public UserExpenses() {
	}

	public UserExpenses(long id, String name, double value, String category, boolean isStaticValue, User user) {
		super(id, name, value, category, isStaticValue);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(user);
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
		UserExpenses other = (UserExpenses) obj;
		return Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UserExpenses [userId=" + user.getId() + ", id=" + id + ", name=" + name + ", value=" + value + ", category="
				+ category + ", isStaticValue=" + isStaticValue + "]";
	}

}
