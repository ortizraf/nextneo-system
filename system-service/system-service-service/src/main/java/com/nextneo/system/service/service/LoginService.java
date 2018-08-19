package com.nextneo.system.service.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nextneo.system.integration.dto.wrapper.LoginDtoWrapper;
import com.nextneo.system.models.entity.User;
import com.nextneo.system.service.message.MessageSpringProperties;
import com.nextneo.system.service.repository.UserRepository;
import com.nextneo.system.utils.crypto.Encryptor;
import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;

/**
* @author  Rafael M Ortiz
*/
@Service
public class LoginService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class.getName());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageSpringProperties message;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User doLogin(LoginDtoWrapper loginDtoWrapper) throws BusinessException{
		Errors errors = new Errors();
		
		LOGGER.info(" doLogin ");
		if(StringUtils.isBlank(loginDtoWrapper.getUserName())){
			errors.addError(message.getMessage("login_username_required"));
		}
		if(StringUtils.isBlank(loginDtoWrapper.getPassword())){
			errors.addError(message.getMessage("login_password_required"));
		}
		if(errors.hasErrors()) {
			throw new BusinessException("invalid data", errors);
		}
		
		User userFind = new User();
		userFind.setLogin(loginDtoWrapper.getUserName());
		userFind.setPassword(Encryptor.encrypt(loginDtoWrapper.getPassword().trim()));
		
		User user = userRepository.findByLoginUser(userFind);
		
		return user;
	}

}
