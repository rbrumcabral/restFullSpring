package com.brum.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brum.domain.entities.User;
import com.brum.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		User response = this.service.findById(id);
		return new ResponseEntity<User>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> response = this.service.findAll();
		return new ResponseEntity<List<User>>(response, HttpStatus.ACCEPTED);
	}

	@PostMapping
	public User create(@Valid @RequestBody  User user) {
		logger.info(user.toString());
		return this.service.create(user);
	}

	@PutMapping
	public User update(@Valid @RequestBody User user) {
		return this.service.update(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.service.delete(id);
	}

}
