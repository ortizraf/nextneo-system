package com.nextneo.system.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.nextneo.system.integration.dto.wrapper.LoginDtoWrapper;
import com.nextneo.system.integration.dto.wrapper.UserLoggedDtoWrapper;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;
import com.nextneo.system.web.exception.HttpClientErrorExceptionHandler;

@Service
public class LoginService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private HttpClientErrorExceptionHandler httpClientErrorExceptionHandler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	public UserLoggedDtoWrapper login(LoginDtoWrapper loginDtoWrapper) throws BusinessException, Exception {
		
		LOGGER.info("login");
		
		UserLoggedDtoWrapper userLoggedDtoWrapper = null;
		try {
			userLoggedDtoWrapper = restTemplate.postForObject(env.getProperty("host.system.service")+ResourcePath.LOGIN+"/"+ResourcePath.Login.DO_LOGIN, loginDtoWrapper, UserLoggedDtoWrapper.class);
		} catch (HttpStatusCodeException e) {			
			httpClientErrorExceptionHandler.handle(e);
		}
		
		return userLoggedDtoWrapper;
		
	}

}
