package com.brum.services;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Messages {

	@Autowired
	MessageSource messageSource;

	public String getMessage(String code, Object... args) {
		return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code) {
		return this.messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

	public String replace(String code, Object... args) {
		return MessageFormat.format(code, args);
	}
}
