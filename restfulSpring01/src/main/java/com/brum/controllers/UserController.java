package com.brum.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brum.domain.dto.v1.UserDTO;
import com.brum.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user/v1")
public class UserController {

	@Autowired
	private UserService service;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
		UserDTO response = this.service.findById(id);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> response = this.service.findAll();
		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.create(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.update(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
