package com.brum.integrationtests.swagger;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brum.configs.TestConfig;
import com.brum.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUIPage() {
		var content = given().basePath("/swagger-ui/index.html").port(TestConfig.SERVER_PORT).when().get().then().statusCode(200)
				.extract().body().asString();
		
		assertTrue(content.contains("Swagger UI"));
	}

}
