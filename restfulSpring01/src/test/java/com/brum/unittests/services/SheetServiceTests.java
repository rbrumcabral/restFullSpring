package com.brum.unittests.services;

import static org.junit.Assert.assertEquals;
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

import com.brum.domain.dto.v1.SheetDTO;
import com.brum.domain.dto.v1.UserDTO;
import com.brum.domain.dto.v2.SheetDTOH;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.User;
import com.brum.mocks.MockSheet;
import com.brum.mocks.MockUser;
import com.brum.repositories.SheetRepository;
import com.brum.services.SheetService;
import com.brum.services.UserService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SheetServiceTests {

	@InjectMocks
	SheetService service;

	@Mock
	UserService userService;

	@Mock
	SheetRepository repository;

	MockSheet mockSheet;

	MockUser mockUser;

	@BeforeEach
	void setUp() throws Exception {
		this.mockSheet = new MockSheet();
		this.mockUser = new MockUser();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByIdOK() {
		User user = mockUser.generateMockUserComplete(1L);
		Sheet sheet = mockSheet.generateMockSheet(user, 1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(sheet));

		var response = this.service.findById(1L);

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getExpenses());
		assertNotNull(response.getName());
		assertNotNull(response.getUserId());

		assertEquals((Long) 1L, response.getKey());
		assertEquals("Sheet1", response.getName());
		assertEquals((Long) 1L, response.getUserId());
		assertEquals((Long) 1L, response.getExpenses().get(0).getKey());
		assertEquals("Expense1", response.getExpenses().get(0).getName());
		assertEquals("Category1", response.getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.getExpenses().get(0).getValue(), 00.1);
		assertEquals((Long) 1L, response.getExpenses().get(0).getSheetId());

	}

	@Test
	void testFindByIdHateoasOK() {
		User user = mockUser.generateMockUserComplete(1L);
		Sheet sheet = mockSheet.generateMockSheet(user, 1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(sheet));

		var response = this.service.findByIdHateoas(1L);

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getExpenses());
		assertNotNull(response.getName());
		assertNotNull(response.getUserId());

		assertEquals((Long) 1L, response.getKey());
		assertEquals("Sheet1", response.getName());
		assertEquals((Long) 1L, response.getUserId());
		assertEquals((Long) 1L, response.getExpenses().get(0).getKey());
		assertEquals("Expense1", response.getExpenses().get(0).getName());
		assertEquals("Category1", response.getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.getExpenses().get(0).getValue(), 00.1);
		assertEquals((Long) 1L, response.getExpenses().get(0).getSheetId());
		assertTrue(response.getLinks().toString().contains("</api/sheet/v1/1>;rel=\"self\""));
	}

	@Test
	void testFindAllOK() {
		User user = mockUser.generateMockUserComplete(1L);
		List<Sheet> sheetList = new ArrayList<Sheet>();

		Sheet sheet = mockSheet.generateMockSheet(user, 1L);
		Sheet sheet2 = mockSheet.generateMockSheet(user, 2L);
		sheetList.add(sheet);
		sheetList.add(sheet2);

		when(this.repository.findAll()).thenReturn(sheetList);

		var response = this.service.findAll();

		assertNotNull(response.get(0));
		assertNotNull(response.get(0).getKey());
		assertNotNull(response.get(0).getExpenses());
		assertNotNull(response.get(0).getName());
		assertNotNull(response.get(0).getUserId());
		assertEquals((Long) 1L, response.get(0).getKey());
		assertEquals("Sheet1", response.get(0).getName());
		assertEquals((Long) 1L, response.get(0).getUserId());
		assertEquals((Long) 1L, response.get(0).getExpenses().get(0).getKey());
		assertEquals("Expense1", response.get(0).getExpenses().get(0).getName());
		assertEquals("Category1", response.get(0).getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.get(0).getExpenses().get(0).getValue(), 0.1);
		assertEquals((Long) 1L, response.get(0).getExpenses().get(0).getSheetId());

		assertNotNull(response.get(1));
		assertNotNull(response.get(1).getKey());
		assertNotNull(response.get(1).getExpenses());
		assertNotNull(response.get(1).getName());
		assertNotNull(response.get(1).getUserId());

		assertEquals((Long) 2L, response.get(1).getKey());
		assertEquals("Sheet2", response.get(1).getName());
		assertEquals((Long) 1L, response.get(1).getUserId());
		assertEquals((Long) 1L, response.get(1).getExpenses().get(0).getKey());
		assertEquals("Expense1", response.get(1).getExpenses().get(0).getName());
		assertEquals("Category1", response.get(1).getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.get(1).getExpenses().get(0).getValue(), 0.1);
		assertEquals((Long) 2L, response.get(1).getExpenses().get(0).getSheetId());

	}

	@Test
	void testFindAllHateoasOK() {
		User user = mockUser.generateMockUserComplete(1L);
		List<Sheet> sheetList = new ArrayList<Sheet>();

		Sheet sheet = mockSheet.generateMockSheet(user, 1L);
		Sheet sheet2 = mockSheet.generateMockSheet(user, 2L);
		sheetList.add(sheet);
		sheetList.add(sheet2);

		when(this.repository.findAll()).thenReturn(sheetList);

		var response = this.service.findAllHateoas();

		assertNotNull(response.get(0));
		assertNotNull(response.get(0).getKey());
		assertNotNull(response.get(0).getExpenses());
		assertNotNull(response.get(0).getName());
		assertNotNull(response.get(0).getUserId());
		assertEquals((Long) 1L, response.get(0).getKey());
		assertEquals("Sheet1", response.get(0).getName());
		assertEquals((Long) 1L, response.get(0).getUserId());
		assertEquals((Long) 1L, response.get(0).getExpenses().get(0).getKey());
		assertEquals("Expense1", response.get(0).getExpenses().get(0).getName());
		assertEquals("Category1", response.get(0).getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.get(0).getExpenses().get(0).getValue(), 0.1);
		assertEquals((Long) 1L, response.get(0).getExpenses().get(0).getSheetId());
		assertTrue(response.get(0).getLinks().toString().contains("</api/sheet/v1/1>;rel=\"self\""));

		assertNotNull(response.get(1));
		assertNotNull(response.get(1).getKey());
		assertNotNull(response.get(1).getExpenses());
		assertNotNull(response.get(1).getName());
		assertNotNull(response.get(1).getUserId());
		assertEquals((Long) 2L, response.get(1).getKey());
		assertEquals("Sheet2", response.get(1).getName());
		assertEquals((Long) 1L, response.get(1).getUserId());
		assertEquals((Long) 1L, response.get(1).getExpenses().get(0).getKey());
		assertEquals("Expense1", response.get(1).getExpenses().get(0).getName());
		assertEquals("Category1", response.get(1).getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.get(1).getExpenses().get(0).getValue(), 0.1);
		assertEquals((Long) 2L, response.get(1).getExpenses().get(0).getSheetId());
		assertTrue(response.get(1).getLinks().toString().contains("</api/sheet/v1/2>;rel=\"self\""));

	}

	@Test
	void testCreateOK() {
		User user = mockUser.generateMockUserComplete(1L);
		Sheet sheet = mockSheet.generateMockSheet(user, 1L);

		when(this.userService.findById(1L)).thenReturn(new UserDTO(user));
		when(this.repository.save(Mockito.any())).thenReturn(sheet);

		var response = this.service.create(new SheetDTO(sheet));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getExpenses());
		assertNotNull(response.getName());
		assertNotNull(response.getUserId());

		assertEquals((Long) 1L, response.getKey());
		assertEquals("Sheet1", response.getName());
		assertEquals((Long) 1L, response.getUserId());
		assertEquals((Long) 1L, response.getExpenses().get(0).getKey());
		assertEquals("Expense1", response.getExpenses().get(0).getName());
		assertEquals("Category1", response.getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.getExpenses().get(0).getValue(), 00.1);
		assertEquals((Long) 1L, response.getExpenses().get(0).getSheetId());

	}

	@Test
	void testCreateHateoasOK() {
		User user = mockUser.generateMockUserComplete(1L);
		Sheet sheet = mockSheet.generateMockSheet(user, 1L);

		when(this.repository.save(Mockito.any())).thenReturn(sheet);
		var response = this.service.createHateoas(new SheetDTOH(sheet));

		assertNotNull(response);
		assertNotNull(response.getKey());
		assertNotNull(response.getExpenses());
		assertNotNull(response.getName());
		assertNotNull(response.getUserId());
		assertEquals((Long) 1L, response.getKey());
		assertEquals("Sheet1", response.getName());
		assertEquals((Long) 1L, response.getUserId());
		assertEquals((Long) 1L, response.getExpenses().get(0).getKey());
		assertEquals("Expense1", response.getExpenses().get(0).getName());
		assertEquals("Category1", response.getExpenses().get(0).getCategory());
		assertEquals(1001.0, response.getExpenses().get(0).getValue(), 00.1);
		assertEquals((Long) 1L, response.getExpenses().get(0).getSheetId());
		assertTrue(response.getLinks().toString().contains("</api/sheet/v1/1>;rel=\"self\""));
	}

	/*
	 * @Test void testUpdateOK() { fail("Not yet implemented"); }
	 * 
	 * @Test void testUpdateHateoasOK() { fail("Not yet implemented"); }
	 * 
	 * @Test void testDeleteOK() { fail("Not yet implemented"); }
	 */
}
