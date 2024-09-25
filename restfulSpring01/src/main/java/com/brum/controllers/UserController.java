package com.brum.controllers;

import java.util.List;

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

import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.dto.v2.UserDTOH;
import com.brum.services.UserService;
import com.brum.util.MediaType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
		UserDTO response = this.service.findById(id);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> response = this.service.findAll();
		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.create(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.update(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTOH> findByIdHateoas(@PathVariable("id") Long id) {
		UserDTOH response = this.service.findByIdHateoas(id);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	public ResponseEntity<List<UserDTOH>> findAllHateoas() {
		List<UserDTOH> response = this.service.findAllHateoas();
		return new ResponseEntity<List<UserDTOH>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTOH> createHateoas(@Valid @RequestBody UserDTOH user) {
		UserDTOH response = this.service.createHateoas(user);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	public ResponseEntity<UserDTOH> updateHateoas(@Valid @RequestBody UserDTOH user) {
		UserDTOH response = this.service.updateHateoas(user);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = {"/v1/{id}", "/v2/{id}"})
	public ResponseEntity<UserDTO> deleteHateoas(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
