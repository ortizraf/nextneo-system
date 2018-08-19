package com.nextneo.system.utils.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author  Rafael M Ortiz
*/
public class Errors implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private List<Error> errors;

	public Errors() {

	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public void addError(String message) {
		if(errors==null) {
			errors = new ArrayList<>();
		}
		Error error = new Error(null, message);
		errors.add(error);
	}
	
	public void addError(Long id, String message) {
		if(errors==null) {
			errors = new ArrayList<>();
		}
		Error error = new Error(id, message);
		errors.add(error);
	}
	
	public boolean hasErrors() {
		return (errors!=null && errors.size()>0 ? true : false);
	}
	
	public void clean() {
		this.errors = new ArrayList<>();
	}

}
