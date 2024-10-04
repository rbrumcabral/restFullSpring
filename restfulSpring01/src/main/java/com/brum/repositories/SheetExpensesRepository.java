package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.SheetExpenses;

@Repository
public interface SheetExpensesRepository extends JpaRepository<SheetExpenses, Long> {
}
