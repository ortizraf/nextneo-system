package com.nextneo.system.web.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;

@Component
public class HttpClientErrorExceptionHandler {

	@Autowired
	private ObjectMapper objectMapper;

	public void handle(HttpStatusCodeException exception) throws BusinessException, Exception {

		if (HttpStatus.PRECONDITION_FAILED.equals(exception.getStatusCode())) {

			
		} else if (HttpStatus.UNPROCESSABLE_ENTITY.equals(exception.getStatusCode())) {
			
			System.out.println(exception.getResponseBodyAsString());

			Errors errors = objectMapper.readValue(exception.getResponseBodyAsString(), Errors.class);

			throw new BusinessException("", errors);

		} else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(exception.getStatusCode())) {



		}

		throw exception;
	}

}