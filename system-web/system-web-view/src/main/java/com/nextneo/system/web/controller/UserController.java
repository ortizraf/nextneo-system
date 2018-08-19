package com.nextneo.system.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextneo.system.integration.dto.UserDto;
import com.nextneo.system.utils.errors.Error;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;
import com.nextneo.system.web.service.UserService;

/**
* @author  Rafael M Ortiz
*/
@Controller
@RequestMapping(ResourcePath.USER)
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = ResourcePath.User.FIND_BY_ID, method = RequestMethod.GET )
	public String findUser() {
		
		LOGGER.info("findUser");

		try {
			UserDto userDto = userService.findById(0l);
			System.out.println("user: "+userDto.getId());
		} catch (BusinessException be) {
			if (be.getErrors() != null && be.getErrors().getErrors() != null && !be.getErrors().getErrors().isEmpty()) {
				for (Error error : be.getErrors().getErrors()) {
					System.out.println("erro: "+error.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "home";
		
	}

}
