package com.nextneo.system.service.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnexpectedExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	void handleServiceException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
		e.printStackTrace();
	    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
	}

}