package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
