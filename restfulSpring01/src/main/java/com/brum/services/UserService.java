package com.brum.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.brum.controllers.UserController;
import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.dto.v2.UserDTOH;
import com.brum.domain.entities.User;
import com.brum.exceptions.entities.NotFoundException;
import com.brum.exceptions.entities.RequiredObjectIsNull;
import com.brum.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private Messages messages;

	@Autowired
	private UserRepository repository;

	public UserDTO findById(Long id) {
		var entity = this.repository.findById(id).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("user.service.findById.notFound", id));
		});
		return new UserDTO(entity);
	}

	public UserDTOH findByIdHateoas(Long id) {
		var dto = this.findById(id);
		var dtoh = new UserDTOH(dto);
		dtoh.add(this.addHateoasLinks(id));
		return dtoh;
	}

	public List<UserDTO> findAll() {
		var responses = this.repository.findAll();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User response : responses) {
			dtos.add(new UserDTO(response));
		}
		return dtos;
	}

	public List<UserDTOH> findAllHateoas() {
		var dtos = this.findAll();
		List<UserDTOH> dtohs = new ArrayList<UserDTOH>();
		for (UserDTO dto : dtos) {
			var dtoh = new UserDTOH(dto);
			dtoh.add(this.addHateoasLinks(dto.getKey()));
			dtohs.add(dtoh);
		}
		return dtohs;
	}

	public UserDTO create(UserDTO user) {

		if (user == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("user.service.create.null.persistence"));
		}

		var entity = user.dtoToEntity();
		var response = this.repository.save(entity);
		return new UserDTO(response);
	}

	public UserDTOH createHateoas(UserDTOH user) {

		if (user == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("user.service.create.null.persistence"));
		}

		var entity = user.dtoToEntity();
		var response = this.repository.save(entity);
		var dto = new UserDTOH(response);
		dto.add(addHateoasLinks(dto.getKey()));
		return dto;
	}

	public UserDTO update(UserDTO user) {
		
		if (user == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("user.service.update.null.persistence"));
		}

		var entity = this.repository.findById(user.getKey()).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("user.service.findById.notFound", user.getKey()));
		});		
		
		entity.setFullName(user.getFullName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setPaymentOptions(user.getPaymentOptions());
		var response = this.repository.save(entity);
		return new UserDTO(response);
	}

	public UserDTOH updateHateoas(UserDTOH user) {
		
		if (user == null) {
			throw new RequiredObjectIsNull(this.messages.getMessage("user.service.update.null.persistence"));
		}
		
		UserDTO userDTO = new UserDTO(user);
		UserDTO updatedUserDTO = this.update(userDTO);
		UserDTOH userDTOH = new UserDTOH(updatedUserDTO);
		userDTOH.add(addHateoasLinks(user.getKey()));
		return userDTOH;
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	private Link addHateoasLinks(long id) {
		return linkTo(methodOn(UserController.class).findById(id)).withSelfRel();
	}

}
