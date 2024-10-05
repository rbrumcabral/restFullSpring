package com.brum.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.brum.controllers.SheetController;
import com.brum.domain.dto.v1.SheetDTO;
import com.brum.domain.dto.v1.SheetExpensesDTO;
import com.brum.domain.dto.v2.SheetDTOH;
import com.brum.domain.entities.Sheet;
import com.brum.exceptions.entities.NotFoundException;
import com.brum.exceptions.entities.RequiredObjectIsNull;
import com.brum.repositories.SheetRepository;
import com.brum.repositories.UserRepository;

@Service
public class SheetService {

	@Autowired
	private Messages messages;

	@Autowired
	private SheetRepository repository;

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(SheetService.class);

	public SheetDTO findById(Long id) {
		var entity = this.repository.findById(id).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("sheet.service.findById.notFound", id));
		});
		return new SheetDTO(entity);
	}

	public SheetDTOH findByIdHateoas(Long id) {
		var dto = this.findById(id);
		var dtoh = new SheetDTOH(dto);
		dtoh.add(this.addHateoasLinks(id));
		return dtoh;
	}

	public List<SheetDTO> findAll() {
		var responses = this.repository.findAll();
		List<SheetDTO> dtos = new ArrayList<SheetDTO>();
		for (Sheet response : responses) {
			dtos.add(new SheetDTO(response));
		}
		return dtos;
	}

	public List<SheetDTOH> findAllHateoas() {
		var dtos = this.findAll();
		List<SheetDTOH> dtohs = new ArrayList<SheetDTOH>();
		for (SheetDTO dto : dtos) {
			var dtoh = new SheetDTOH(dto);
			dtoh.add(this.addHateoasLinks(dto.getKey()));
			dtohs.add(dtoh);
		}
		return dtohs;
	}

	public SheetDTO create(SheetDTO sheet) {

		if (sheet == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("sheet.service.create.null.persistence"));
		}

		this.logger.info(sheet.toString());

		var userEntity = this.userRepository.findById(sheet.getUserId()).orElseThrow(() -> {
			throw new NotFoundException(
					this.messages.getMessage("sheet.service.create.findById.userId.notFound", sheet.getKey()));
		});

		var entity = sheet.dtoToEntity();
		entity.setUser(userEntity);

		var response = this.repository.save(entity);
		return new SheetDTO(response);
	}

	public SheetDTOH createHateoas(SheetDTOH sheet) {
		if (sheet == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("sheet.service.create.null.persistence"));
		}

		var entity = sheet.dtoToEntity();

		var response = this.repository.save(entity);
		var dto = new SheetDTOH(response);
		dto.add(addHateoasLinks(dto.getKey()));
		return dto;
	}

	public SheetDTO update(SheetDTO sheet) {

		if (sheet == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("sheet.service.update.null.persistence"));
		}

		if (sheet.getUserId() == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("sheet.service.update.userId.null"));
		}

		var entity = this.repository.findById(sheet.getKey()).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("sheet.service.findById.notFound", sheet.getKey()));
		});

		var userEntity = this.userRepository.findById(sheet.getUserId()).orElseThrow(() -> {
			throw new NotFoundException(
					this.messages.getMessage("sheet.service.update.findById.userId.notFound", sheet.getKey()));
		});

		entity.setId(sheet.getKey());
		entity.setName(sheet.getName());
		entity.setUser(userEntity);

		if (sheet.getExpenses() != null) {
			entity.setExpenses(
					sheet.getExpenses().stream().map(SheetExpensesDTO::dtoToEntity).collect(Collectors.toList()));
		}

		var response = this.repository.save(entity);
		return new SheetDTO(response);
	}

	public SheetDTOH updateHateoas(SheetDTOH sheet) {

		if (sheet == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("sheet.service.update.null.persistence"));
		}

		SheetDTO sheetDTO = new SheetDTO(sheet);
		SheetDTO updatedSheetDTO = this.update(sheetDTO);
		SheetDTOH sheetDTOH = new SheetDTOH(updatedSheetDTO);
		sheetDTOH.add(addHateoasLinks(sheet.getKey()));
		return sheetDTOH;
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	private Link addHateoasLinks(long id) {
		return linkTo(methodOn(SheetController.class).findById(id)).withSelfRel();
	}

}
