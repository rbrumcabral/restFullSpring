package com.brum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brum.services.LanguageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/lang")
public class LanguageController {

	@Autowired
	private LanguageService service;

	@GetMapping("/change")
	public String changeLanguage(@RequestParam(value = "lang", required = false) String langParam,
			@RequestHeader(value = "lang", required = false) String langHeader, HttpServletRequest request,
			HttpServletResponse response) {

		return this.service.changeLanguage(langParam, langHeader, request, response);
	}

	@GetMapping
	public String getLanguage(HttpServletRequest request) {
		return this.service.getLanguage(request);
	}
}