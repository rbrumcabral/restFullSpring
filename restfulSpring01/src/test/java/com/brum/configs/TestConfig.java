package com.brum.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	public static final int SERVER_PORT = 8888;

	public static final String HEADER_PARAM_AUTHORIZATION = "Authorization";
	
	public static final String HEADER_PARAM_ORIGIN = "Origin";
	
	public static final String ORIGIN_LOCALHOST = "http://localhost:8080";
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String CONTENT_TYPE_XML = "application/xml";
	
	public static final String CONTENT_TYPE_YAML = "application/x-yaml";

}
