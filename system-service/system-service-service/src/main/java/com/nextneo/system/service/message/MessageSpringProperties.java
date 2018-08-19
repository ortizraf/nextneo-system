package com.nextneo.system.service.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
* @author  Rafael M Ortiz
*/
@Component
public class MessageSpringProperties {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String key) {
		Locale enUS = new Locale("en", "US");
		return messageSource.getMessage(key, null, enUS);
	}

	public String getMessageBR(String key) {
		Locale ptBR = new Locale("pt", "BR");
		return messageSource.getMessage(key, null, ptBR);
	}

	public String getMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

}
