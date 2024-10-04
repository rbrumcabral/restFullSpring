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
	    private Long key;
	    private String name;
	    private double value;
	    private String category;
	    private boolean isStaticValue;
	    private User user;

	    public UserExpensesDTOH() {
	    }

	    public UserExpensesDTOH(String name, double value, String category, boolean isStaticValue, User user) {
	        this.name = name;
	        this.value = value;
	        this.category = category;
	        this.isStaticValue = isStaticValue;
	        this.user = user;
	    }

	    public UserExpensesDTOH(UserExpenses userExpenses) {
	        this.key = userExpenses.getId();
	        this.name = userExpenses.getName();
	        this.value = userExpenses.getValue();
	        this.category = userExpenses.getCategory();
	        this.isStaticValue = userExpenses.isStaticValue();
	        this.user = userExpenses.getUser();
	    }
	    
	    public UserExpensesDTOH(UserExpensesDTO userExpenses) {
	        this.key = userExpenses.getKey();
	        this.name = userExpenses.getName();
	        this.value = userExpenses.getValue();
	        this.category = userExpenses.getCategory();
	        this.isStaticValue = userExpenses.isStaticValue();
	        this.user = userExpenses.getUser();
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

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public UserExpenses dtoToEntity() {
	        return new UserExpenses(this.key, this.name, this.value, this.category, this.isStaticValue, this.user);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(category, key, name, user, value, isStaticValue);
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
	        return Objects.equals(category, other.category) && key.equals(other.key) && Objects.equals(name, other.name)
	                && Objects.equals(user, other.user) && Double.compare(value, other.value) == 0
	                && isStaticValue == other.isStaticValue;
	    }

	    @Override
	    public String toString() {
	        return "UserExpensesDTO [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category
	                + ", isStaticValue=" + isStaticValue + ", user=" + user + "]";
	    }
	}
