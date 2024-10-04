package com.brum.domain.dto.v2;

import java.util.Objects;

import com.brum.domain.dto.v1.PaymentOptionDTO;
import com.brum.domain.entities.PaymentOption;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "creditLimit", "user" })
public class PaymentOptionDTOH {
	@JsonProperty("id")
    private Long key;
    private String name;
    private double creditLimit;
    private User user;

    public PaymentOptionDTOH() {
    }

    public PaymentOptionDTOH(String name, double creditLimit, User user) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.user = user;
    }

    public PaymentOptionDTOH(PaymentOption paymentOption) {
        this.key = paymentOption.getId();
        this.name = paymentOption.getName();
        this.creditLimit = paymentOption.getCreditLimit();
        this.user = paymentOption.getUser();
    }
    
    public PaymentOptionDTOH(PaymentOptionDTO paymentOption) {
        this.key = paymentOption.getKey();
        this.name = paymentOption.getName();
        this.creditLimit = paymentOption.getCreditLimit();
        this.user = paymentOption.getUser();
    }
    
    public PaymentOptionDTOH(PaymentOptionDTOH paymentOption) {
        this.key = paymentOption.getKey();
        this.name = paymentOption.getName();
        this.creditLimit = paymentOption.getCreditLimit();
        this.user = paymentOption.getUser();
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

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentOption dtoToEntity() {
        return new PaymentOption(this.key, this.name, this.creditLimit, this.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditLimit, key, name, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentOptionDTOH other = (PaymentOptionDTOH) obj;
        return Double.compare(other.creditLimit, creditLimit) == 0 && Objects.equals(key, other.key)
                && Objects.equals(name, other.name) && Objects.equals(user, other.user);
    }

    @Override
    public String toString() {
        return "PaymentOptionDTO [key=" + key + ", name=" + name + ", creditLimit=" + creditLimit + ", user=" + user + "]";
    }
}
