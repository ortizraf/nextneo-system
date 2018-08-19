package com.nextneo.system.utils.exception;

import com.nextneo.system.utils.errors.Errors;

/**
* @author  Rafael M Ortiz
* @version 1.0
*/
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Errors errors;
	
	public BusinessException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}
	
	public BusinessException(String message, Throwable cause, Errors errors) {
		super(message, cause);
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	
}
