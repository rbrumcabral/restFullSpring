package com.brum.integrationtests.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.brum.configs.TestConfig;
import com.brum.integrationtests.DTO.UserDTO;
import com.brum.integrationtests.testcontainers.AbstractIntegrationTest;
import com.brum.mocks.MockUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class UserControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;

	private static ObjectMapper objectMapper;

	private static UserDTO user;

	private MockUser mockUser;

	@BeforeAll
	private static void setup() {
		objectMapper = new ObjectMapper();
		user = new UserDTO();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockUser();

		specification = new RequestSpecBuilder().addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_LOCALHOST)
				.setBasePath("/api/user/v1").setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification).contentType(TestConfig.CONTENT_TYPE_JSON).body(user).when().post()
				.then().statusCode(201).extract().body().asString();

		UserDTO createdUser = objectMapper.readValue(content, UserDTO.class);
		user = createdUser;

		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());
		assertTrue(createdUser.getId() > 0);
		assertNotNull(createdUser.getFullName());
		assertNotNull(createdUser.getEmail());
		assertNotNull(createdUser.getPassword());
		assertNotNull(createdUser.getInvestments());
		assertNotNull(createdUser.getPaymentOptions());
		assertNotNull(createdUser.getSavedExpenses());
		assertNotNull(createdUser.getSheets());
		assertEquals("User1", createdUser.getFullName());
		assertEquals((Long) 1L, createdUser.getId(), (Long) 1L);
		assertEquals("user1@example.com", createdUser.getEmail());
		assertEquals("pass1", createdUser.getPassword());

	}

	@Test
	@Order(2)
	public void testCreateWithStrangeOrigin() throws JsonMappingException, JsonProcessingException {
		mockUser();

		specification = new RequestSpecBuilder().addHeader(TestConfig.HEADER_PARAM_ORIGIN, "http://teste.com.br")
				.setBasePath("/api/user/v1").setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification).contentType(TestConfig.CONTENT_TYPE_JSON).body(user).when().post()
				.then().statusCode(403).extract().body().asString();

		assertNotNull(content);
		assertEquals("Invalid CORS request", content);

	}

	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockUser();

		specification = new RequestSpecBuilder().addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_LOCALHOST)
				.setBasePath("/api/user/v1").setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification).contentType(TestConfig.CONTENT_TYPE_JSON)
				.pathParam("id", user.getId()).when().get("{id}").then().statusCode(200).extract().body().asString();

		UserDTO createdUser = objectMapper.readValue(content, UserDTO.class);
		user = createdUser;

		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());
		assertTrue(createdUser.getId() > 0);
		assertNotNull(createdUser.getFullName());
		assertNotNull(createdUser.getEmail());
		assertNotNull(createdUser.getPassword());
		assertNotNull(createdUser.getInvestments());
		assertNotNull(createdUser.getPaymentOptions());
		assertNotNull(createdUser.getSavedExpenses());
		assertNotNull(createdUser.getSheets());
		assertEquals("User1", createdUser.getFullName());
		assertEquals((Long) 1L, createdUser.getId(), (Long) 1L);
		assertEquals("user1@example.com", createdUser.getEmail());
		assertEquals("pass1", createdUser.getPassword());

	}
	
	@Test
	@Order(4)
	public void testFindByIdWithStrangeOrigin() throws JsonMappingException, JsonProcessingException {
		mockUser();

		specification = new RequestSpecBuilder().addHeader(TestConfig.HEADER_PARAM_ORIGIN, "http://teste.com.br")
				.setBasePath("/api/user/v1").setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification).contentType(TestConfig.CONTENT_TYPE_JSON).pathParam("id", user.getId()).when().get("{id}")
				.then().statusCode(403).extract().body().asString();

		assertNotNull(content);
		assertEquals("Invalid CORS request", content);

	}

	private void mockUser() {
		mockUser = new MockUser();
		var userEntity = mockUser.generateMockUser(1L);
		user = new UserDTO(userEntity);
	}
}
