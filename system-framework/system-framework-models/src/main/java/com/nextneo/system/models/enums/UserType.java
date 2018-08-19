package com.nextneo.system.models.enums;

import java.util.Locale;

import com.nextneo.system.utils.messages.MessageProperties;

public enum UserType {
	
	CUSTOMER("user_customer"),
	MANAGER("user_manager");
	
	private UserType(String name){
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return MessageProperties.getMessage(name);
	}
	
	public String getName(Locale locale) {
		return MessageProperties.getMessage(name, locale);
	}

	public void setName(String name) {
		this.name = name;
	}

}
