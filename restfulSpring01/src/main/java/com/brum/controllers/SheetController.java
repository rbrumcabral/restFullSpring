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

import com.brum.domain.dto.v1.SheetDTO;
import com.brum.domain.dto.v2.SheetDTOH;
import com.brum.services.SheetService;
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
@Tag(name = "Sheet", description = "Endpoints for managing Sheets")
public class SheetController {

	@Autowired
	private SheetService service;

	@GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds a Sheet V1", description = "Finds a Sheet V1", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTO.class)) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTO> findById(@PathVariable("id") Long id) {
		SheetDTO response = this.service.findById(id);
		return new ResponseEntity<SheetDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds all Sheets V1", description = "Finds all Sheets V1", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SheetDTO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<List<SheetDTO>> findAll() {
		List<SheetDTO> response = this.service.findAll();
		return new ResponseEntity<List<SheetDTO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Creates a Sheet V1", description = "Creates a Sheet V1", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTO.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTO> create(@Valid @RequestBody SheetDTO user) {
		SheetDTO response = this.service.create(user);
		return new ResponseEntity<SheetDTO>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v1", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Updates a Sheet V1", description = "Updates a Sheet V1", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTO.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTO> update(@Valid @RequestBody SheetDTO user) {
		SheetDTO response = this.service.update(user);
		return new ResponseEntity<SheetDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds a Sheet V2", description = "Finds a Sheet V2", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTOH> findByIdHateoas(@PathVariable("id") Long id) {
		SheetDTOH response = this.service.findByIdHateoas(id);
		return new ResponseEntity<SheetDTOH>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds all Sheets V2", description = "Finds all Sheets V2", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SheetDTOH.class))) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<List<SheetDTOH>> findAllHateoas() {
		List<SheetDTOH> response = this.service.findAllHateoas();
		return new ResponseEntity<List<SheetDTOH>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Creates a Sheet V2", description = "Creates a Sheet V2", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTOH> createHateoas(@Valid @RequestBody SheetDTOH user) {
		SheetDTOH response = this.service.createHateoas(user);
		return new ResponseEntity<SheetDTOH>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YAML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YAML })
	@Operation(summary = "Updates a Sheet V2", description = "Updates a Sheet V2", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SheetDTOH.class)) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTOH> updateHateoas(@Valid @RequestBody SheetDTOH user) {
		SheetDTOH response = this.service.updateHateoas(user);
		return new ResponseEntity<SheetDTOH>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/v1/{id}", "/v2/{id}" })
	@Operation(summary = "Deletes a Sheet V1/V2", description = "Deletes a Sheet V1/V2", tags = { "Sheet" }, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<SheetDTO> deleteHateoas(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
