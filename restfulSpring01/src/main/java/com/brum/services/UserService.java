package com.brum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.domain.entities.User;
import com.brum.exceptions.entities.NotFoundException;
import com.brum.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private Messages messages;

	@Autowired
	private UserRepository repository;

	public User findById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("user.service.findById.notFound", id));
		});
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}

	public User create(User user) {
		return this.repository.save(user);
	}

	public User update(User user) {
		User entity = this.repository.findById(user.getId()).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("user.service.findById.notFound", user.getId()));
		});

		entity.setFullName(user.getFullName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setPaymentOptions(user.getPaymentOptions());

		return this.repository.save(entity);
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

}
