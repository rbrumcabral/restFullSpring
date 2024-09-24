package com.brum.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.domain.dto.UserDTO;
import com.brum.domain.entities.User;
import com.brum.exceptions.entities.NotFoundException;
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

	public List<UserDTO> findAll() {
		var responses = this.repository.findAll();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User response : responses) {
			dtos.add(new UserDTO(response));
		}

		return dtos;
	}

	public UserDTO create(UserDTO user) {
		var entity = user.dtoToEntity();
		var response = this.repository.save(entity);
		return new UserDTO(response);
	}

	public UserDTO update(UserDTO user) {
		var entity = this.repository.findById(user.getId()).orElseThrow(() -> {
			throw new NotFoundException(this.messages.getMessage("user.service.findById.notFound", user.getId()));
		});

		entity.setFullName(user.getFullName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setPaymentOptions(user.getPaymentOptions());

		var response = this.repository.save(entity);

		return new UserDTO(response);
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}

}
