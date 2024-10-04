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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
@Tag(name = "User", description = "Endpoints for managing Users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds a User V1", description = "Finds a User V1", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
		UserDTO response = this.service.findById(id);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds all Users V1", description = "Finds all Users V1", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> response = this.service.findAll();
		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Creates a User V1", description = "Creates a User V1", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.create(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Updates a User V1", description = "Updates a User V1", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) {
		UserDTO response = this.service.update(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds a User V2", description = "Finds a User V2", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTOH> findByIdHateoas(@PathVariable("id") Long id) {
		UserDTOH response = this.service.findByIdHateoas(id);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds all Users V2", description = "Finds all Users V2", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTOH.class))) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<List<UserDTOH>> findAllHateoas() {
		List<UserDTOH> response = this.service.findAllHateoas();
		return new ResponseEntity<List<UserDTOH>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Creates a User V2", description = "Creates a User V2", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTOH> createHateoas(@Valid @RequestBody UserDTOH user) {
		UserDTOH response = this.service.createHateoas(user);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Updates a User V2", description = "Updates a User V2", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTOH> updateHateoas(@Valid @RequestBody UserDTOH user) {
		UserDTOH response = this.service.updateHateoas(user);
		return new ResponseEntity<UserDTOH>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/v1/{id}", "/v2/{id}" })
	@Operation(summary = "Deletes a User V1/V2", description = "Deletes a User V1/V2", tags = { "User" }, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<UserDTO> deleteHateoas(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
