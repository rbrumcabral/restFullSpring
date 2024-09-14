package com.brum.domain.entities;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private long id;

	private String username;

	private String email;
	
	private List<PaymentOption> paymentOptions;
}
