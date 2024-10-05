package com.brum.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.dto.v2.UserDTOH;
import com.brum.domain.entities.User;
import com.brum.exceptions.entities.RequiredObjectIsNull;
import com.brum.mocks.MockUser;
import com.brum.repositories.UserRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

	MockUser mockUser;

	@InjectMocks
	private UserService service;

	@Mock
	private Messages messages;

	@Mock
	private UserRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		this.mockUser = new MockUser();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByIdOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);

		when(this.repository.findById((Long) 1L)).thenReturn(Optional.of(user));

		var response = this.service.findById((Long) 1L);

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertNotNull(response.getInvestments());
		assertNotNull(response.getPaymentOptions());
		assertNotNull(response.getSavedExpenses());
		assertNotNull(response.getSheets());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
	}

	@Test
	void testFindByIdHateoasOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);
		when(this.repository.findById((Long) 1L)).thenReturn(Optional.of(user));

		var response = this.service.findByIdHateoas((Long) 1L);

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
		assertTrue(response.getLinks().toString().contains("</api/user/v1/1>;rel=\"self\""));
	}

	@Test
	void testFindAll() {
		List<User> userList = new ArrayList<User>();
		User user = mockUser.generateMockUserComplete((Long) 1L);
		User user2 = mockUser.generateMockUserComplete((Long) 2L);
		userList.add(user);
		userList.add(user2);

		when(this.repository.findAll()).thenReturn(userList);

		var response = this.service.findAll();

		assertNotNull(response.get(0));
		assertNotNull(response.get(0).getKey());
		assertNotNull(response.get(0).getFullName());
		assertNotNull(response.get(0).getEmail());
		assertNotNull(response.get(0).getPassword());
		assertNotNull(response.get(0).getInvestments());
		assertNotNull(response.get(0).getPaymentOptions());
		assertNotNull(response.get(0).getSavedExpenses());
		assertNotNull(response.get(0).getSheets());
		assertEquals("User1", response.get(0).getFullName());
		assertEquals((Long) 1L, response.get(0).getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.get(0).getEmail());
		assertEquals("pass1", response.get(0).getPassword());
		assertEquals((Long) 1L, response.get(0).getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.get(0).getInvestments().getFirst().getName());
		assertEquals(1001L, response.get(0).getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.get(0).getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.get(0).getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.get(0).getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.get(0).getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.get(0).getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.get(0).getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.get(0).getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.get(0).getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.get(0).getPaymentOptions().getFirst().getCreditLimit(), 0.01);

		assertNotNull(response.get(1));
		assertNotNull(response.get(1).getKey());
		assertNotNull(response.get(1).getFullName());
		assertNotNull(response.get(1).getEmail());
		assertNotNull(response.get(1).getPassword());
		assertNotNull(response.get(1).getInvestments());
		assertNotNull(response.get(1).getPaymentOptions());
		assertNotNull(response.get(1).getSavedExpenses());
		assertNotNull(response.get(1).getSheets());
		assertEquals("User2", response.get(1).getFullName());
		assertEquals((Long) 2L, response.get(1).getKey(), (Long) 1L);
		assertEquals("user2@example.com", response.get(1).getEmail());
		assertEquals("pass2", response.get(1).getPassword());
		assertEquals((Long) 1L, response.get(1).getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.get(1).getInvestments().getFirst().getName());
		assertEquals(1001L, response.get(1).getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.get(1).getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.get(1).getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.get(1).getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.get(1).getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.get(1).getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.get(1).getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.get(1).getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.get(1).getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.get(1).getPaymentOptions().getFirst().getCreditLimit(), 0.01);

	}

	@Test
	void testFindAllHateoas() {
		List<User> userList = new ArrayList<User>();
		User user = mockUser.generateMockUserComplete((Long) 1L);
		User user2 = mockUser.generateMockUserComplete((Long) 2L);
		userList.add(user);
		userList.add(user2);

		when(this.repository.findAll()).thenReturn(userList);

		var response = this.service.findAllHateoas();

		assertNotNull(response.get(0));
		assertNotNull(response.get(0).getKey());
		assertNotNull(response.get(0).getFullName());
		assertNotNull(response.get(0).getEmail());
		assertNotNull(response.get(0).getPassword());
		assertNotNull(response.get(0).getInvestments());
		assertNotNull(response.get(0).getPaymentOptions());
		assertNotNull(response.get(0).getSavedExpenses());
		assertNotNull(response.get(0).getSheets());
		assertEquals("User1", response.get(0).getFullName());
		assertEquals((Long) 1L, response.get(0).getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.get(0).getEmail());
		assertEquals("pass1", response.get(0).getPassword());
		assertEquals((Long) 1L, response.get(0).getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.get(0).getInvestments().getFirst().getName());
		assertEquals(1001L, response.get(0).getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.get(0).getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.get(0).getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.get(0).getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.get(0).getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.get(0).getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.get(0).getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.get(0).getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.get(0).getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.get(0).getPaymentOptions().getFirst().getCreditLimit(), 0.01);
		assertTrue(response.get(0).getLinks().toString().contains("</api/user/v1/1>;rel=\"self\""));

		assertNotNull(response.get(1));
		assertNotNull(response.get(1).getKey());
		assertNotNull(response.get(1).getFullName());
		assertNotNull(response.get(1).getEmail());
		assertNotNull(response.get(1).getPassword());
		assertNotNull(response.get(1).getInvestments());
		assertNotNull(response.get(1).getPaymentOptions());
		assertNotNull(response.get(1).getSavedExpenses());
		assertNotNull(response.get(1).getSheets());
		assertEquals("User2", response.get(1).getFullName());
		assertEquals((Long) 2L, response.get(1).getKey(), (Long) 1L);
		assertEquals("user2@example.com", response.get(1).getEmail());
		assertEquals("pass2", response.get(1).getPassword());
		assertEquals((Long) 1L, response.get(1).getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.get(1).getInvestments().getFirst().getName());
		assertEquals(1001L, response.get(1).getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.get(1).getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.get(1).getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.get(1).getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.get(1).getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.get(1).getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.get(1).getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.get(1).getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.get(1).getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.get(1).getPaymentOptions().getFirst().getCreditLimit(), 0.01);
		assertTrue(response.get(1).getLinks().toString().contains("</api/user/v1/2>;rel=\"self\""));

	}

	@Test
	void testCreateOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);
		when(this.repository.save(Mockito.any())).thenReturn(user);

		var response = this.service.create(new UserDTO(user));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey());
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
	}

	@Test
	void testCreateWithNull() {

		when(this.messages.getMessage("user.service.create.null.persistence")).thenReturn("Mensagem da exceção");

		Exception exception = assertThrows(RequiredObjectIsNull.class, () -> this.service.create(null));

		String expectedMessage = "Mensagem da exceção";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	void testCreateHateoasOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);
		when(this.repository.save(Mockito.any())).thenReturn(user);

		var response = this.service.createHateoas(new UserDTOH(user));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
		assertTrue(response.getLinks().toString().contains("</api/user/v1/1>;rel=\"self\""));
	}

	@Test
	void testCreateHateoasWithNull() {
		when(this.messages.getMessage("user.service.create.null.persistence")).thenReturn("Mensagem da exceção");

		Exception exception = assertThrows(RequiredObjectIsNull.class, () -> this.service.createHateoas(null));

		String expectedMessage = "Mensagem da exceção";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);
		when(this.repository.save(user)).thenReturn(user);
		when(this.repository.findById((Long) 1L)).thenReturn(Optional.of(user));

		var response = this.service.update(new UserDTO(user));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
	}

	@Test
	void testUpdateWithNull() {
		when(this.messages.getMessage("user.service.update.null.persistence")).thenReturn("Mensagem da exceção");

		Exception exception = assertThrows(RequiredObjectIsNull.class, () -> this.service.update(null));

		String expectedMessage = "Mensagem da exceção";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateHateoasOK() {
		User user = mockUser.generateMockUserComplete((Long) 1L);
		when(this.repository.save(user)).thenReturn(user);
		when(this.repository.findById((Long) 1L)).thenReturn(Optional.of(user));

		var response = this.service.updateHateoas(new UserDTOH(user));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getFullName());
		assertNotNull(response.getEmail());
		assertNotNull(response.getPassword());
		assertEquals("User1", response.getFullName());
		assertEquals((Long) 1L, response.getKey(), (Long) 1L);
		assertEquals("user1@example.com", response.getEmail());
		assertEquals("pass1", response.getPassword());
		assertEquals((Long) 1L, response.getInvestments().getFirst().getKey());
		assertEquals("Investment1", response.getInvestments().getFirst().getName());
		assertEquals(1001L, response.getInvestments().getFirst().getValue(), 0.001);
		assertEquals("Category1", response.getInvestments().getFirst().getCategory());
		assertEquals("Sheet1", response.getSheets().getFirst().getName());
		assertEquals((Long) 1L, response.getSavedExpenses().getFirst().getKey());
		assertEquals("Expense1", response.getSavedExpenses().getFirst().getName());
		assertEquals("Category1", response.getSavedExpenses().getFirst().getCategory());
		assertEquals(1001L, response.getSavedExpenses().getFirst().getValue(), 0.01);
		assertFalse(response.getSavedExpenses().getFirst().isStaticValue());
		assertEquals("PaymentOption1", response.getPaymentOptions().getFirst().getName());
		assertEquals(1001L, response.getPaymentOptions().getFirst().getCreditLimit(), 0.01);
		assertTrue(response.getLinks().toString().contains("</api/user/v1/1>;rel=\"self\""));
	}

	@Test
	void testUpdateHateoasWithNull() {
		when(this.messages.getMessage("user.service.update.null.persistence")).thenReturn("Mensagem da exceção");

		Exception exception = assertThrows(RequiredObjectIsNull.class, () -> this.service.updateHateoas(null));

		String expectedMessage = "Mensagem da exceção";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
