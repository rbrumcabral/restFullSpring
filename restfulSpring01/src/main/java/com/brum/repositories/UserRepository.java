package com.brum.repositories;

import org.springframework.stereotype.Repository;

import com.brum.domain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


}
