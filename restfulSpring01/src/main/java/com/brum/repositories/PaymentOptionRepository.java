package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.PaymentOption;

@Repository
public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {
}
