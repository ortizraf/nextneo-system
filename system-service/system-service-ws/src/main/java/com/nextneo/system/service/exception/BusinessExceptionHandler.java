package com.nextneo.system.service.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;

@ControllerAdvice
public class BusinessExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public Errors handleServiceException(HttpServletRequest req, HttpServletResponse response,
			BusinessException businessException) throws Exception {
		if (businessException.getErrors() == null) {
			businessException.setErrors(new Errors());
		}
		if (!businessException.getErrors().hasErrors()) {
			businessException.getErrors().addError(businessException.getMessage());
		}
		response.setStatus(422);
		return businessException.getErrors();
	}

}