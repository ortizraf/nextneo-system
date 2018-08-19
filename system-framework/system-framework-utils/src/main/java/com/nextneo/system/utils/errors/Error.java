package com.nextneo.system.utils.errors;

import java.io.Serializable;

/**
* @author  Rafael M Ortiz
*/
public class Error implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long code;
	
	private String message;
	
	public Error() {
		
	}
	
	public Error(Long code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
