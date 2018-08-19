package com.nextneo.system.web.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;

@Component
public class HttpExceptionErrorHandler implements ResponseErrorHandler {
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {

			System.out.println("teste 422");
			
			Errors errors = new Errors();
			errors.addError("teste");
			
			throw new BusinessException("teste", errors);

		} else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {

			System.out.println("teste 500");
			
			throw new RuntimeException();

		}
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() != HttpStatus.OK
				&& response.getStatusCode() != HttpStatus.CREATED
				&& response.getStatusCode() != HttpStatus.ACCEPTED) {
			return true;
		}	
		return false;
	}

}
