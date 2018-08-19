package com.nextneo.system.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.nextneo.system.integration.dto.UserDto;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;
import com.nextneo.system.web.exception.HttpClientErrorExceptionHandler;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private HttpClientErrorExceptionHandler httpClientErrorExceptionHandler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	
	public UserDto findById(Long id) throws BusinessException, Exception {
		
		LOGGER.info("findById");
		
		UserDto userDto = null;
		try {
			userDto = restTemplate.getForObject(env.getProperty("host.system.service")+ResourcePath.USER+"/"+ResourcePath.User.FIND_BY_ID, UserDto.class, id);
		} catch (HttpStatusCodeException e) {			
			httpClientErrorExceptionHandler.handle(e);
		}
		
		return userDto;
		
	}

}
