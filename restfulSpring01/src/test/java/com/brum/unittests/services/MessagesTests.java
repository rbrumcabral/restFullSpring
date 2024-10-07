package com.brum.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.brum.services.Messages;

@ExtendWith(MockitoExtension.class)
public class MessagesTests {

	@Mock
	private MessageSource messageSource;

	@InjectMocks
	private Messages messages;

	@BeforeEach
	public void setUp() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
	}

	@Test
	public void testGetMessageWithArgs() {
		when(messageSource.getMessage("greeting", new Object[] { "Jack" }, Locale.ENGLISH)).thenReturn("Hello, Jack!");

		String result = messages.getMessage("greeting", "Jack");

		assertEquals("Hello, Jack!", result);
	}

	@Test
	public void testGetMessageWithoutArgs() {
		when(messageSource.getMessage("hello.world", null, Locale.ENGLISH)).thenReturn("Hello, World!");

		String result = messages.getMessage("hello.world");

		assertEquals("Hello, World!", result);
	}

	@Test
	public void testReplace() {
		String template = "Hello, {0}!";
		Object[] arguments = { "Alice" };
		String expected = "Hello, Alice!";

		String result = messages.replace(template, arguments);

		assertEquals(expected, result);
	}
}
