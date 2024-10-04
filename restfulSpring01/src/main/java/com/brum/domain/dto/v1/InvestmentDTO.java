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
    private Long key;
    private String name;
    private double value;
    private String category;
    private User user;

    public InvestmentDTO() {
        super();
    }

    public InvestmentDTO(String name, double value, String category, User user) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.user = user;
    }

    public InvestmentDTO(Investment investment) {
        this.key = investment.getId();
        this.name = investment.getName();
        this.value = investment.getValue();
        this.category = investment.getCategory();
        this.user = investment.getUser();
    }
    
    public InvestmentDTO(InvestmentDTOH investment) {
        this.key = investment.getKey();
        this.name = investment.getName();
        this.value = investment.getValue();
        this.category = investment.getCategory();
        this.user = investment.getUser();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Investment dtoToEntity() {
        return new Investment(this.key, this.name, this.value, this.category, this.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, key, name, user, value);
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
        return Objects.equals(category, other.category) && key.equals(other.key) && Objects.equals(name, other.name)
                && Objects.equals(user, other.user) && Double.compare(value, other.value) == 0;
    }

    @Override
    public String toString() {
        return "InvestmentDTO [key=" + key + ", name=" + name + ", value=" + value + ", category=" + category + ", user=" + user + "]";
    }
}
