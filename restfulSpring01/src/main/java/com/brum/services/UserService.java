package com.brum.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.domain.models.User;
import com.brum.exceptions.entities.NotFoundException;
import com.brum.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public User findById(Integer id) {
		Optional<User> response = this.repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: User id: " + id));
	}

}
