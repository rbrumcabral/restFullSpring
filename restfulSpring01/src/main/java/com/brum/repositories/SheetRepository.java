package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.Sheet;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Long> {
}
