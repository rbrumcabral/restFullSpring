package com.brum.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Restful Spring")
						.version("v1")
						.description("Projeto base para especialização em RestFul-Hateoas com Spring Boot")
						.termsOfService("https://github.com/rbrumcabral/restfulSpring")
						.license(new License()
								.name("Apache 2.0")
								.url("https://github.com/rbrumcabral/restfulSpring")
								)
						);
	}
	
}
