package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.JwtUser;

@Repository
public interface JwtUserRepository extends JpaRepository<JwtUser, Long> {
	
	@Query("SELECT u FROM JwtUser u WHERE u.userName =:userName")
	JwtUser findByUsername(@Param("userName") String username);
}
