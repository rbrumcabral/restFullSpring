package com.brum.services;

import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LanguageService {

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private Messages messages;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(LanguageService.class);

	private String ERROR_IDIOM_NOT_FOUND;

	private String ACTUAL_IDIOM;

	private String IDIOM_CHANGED_TO;

	public String changeLanguage(String langParam, String langHeader, HttpServletRequest request,
			HttpServletResponse response) {

		String lang = langHeader != null ? langHeader : langParam;

		if (lang != null) {
			Locale newLocale = Locale.forLanguageTag(lang);
			localeResolver.setLocale(request, response, newLocale);
			return this.messages.getMessage("messages.idiom_changed_to", lang);
		}

		return this.messages.getMessage("messages.error_idiom_not_found");
	}

	public String getLanguage(HttpServletRequest request) {
		Locale currentLocale = localeResolver.resolveLocale(request);
		return this.messages.getMessage("messages.actual_idiom", currentLocale.toLanguageTag());
	}

}
