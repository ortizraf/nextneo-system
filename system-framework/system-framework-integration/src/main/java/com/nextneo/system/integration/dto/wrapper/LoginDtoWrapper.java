package com.nextneo.system.integration.dto.wrapper;

import java.io.Serializable;

public class LoginDtoWrapper implements Serializable {
	
	private static final long serialVersionUID = -2724617536498223286L;

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginDtoWrapper() {
		
	}
	
	public LoginDtoWrapper(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
}