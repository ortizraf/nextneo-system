package com.nextneo.system.web.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nextneo.system.integration.dto.wrapper.LoginDtoWrapper;
import com.nextneo.system.integration.dto.wrapper.UserLoggedDtoWrapper;
import com.nextneo.system.utils.errors.Error;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;
import com.nextneo.system.web.service.LoginService;

@Controller
@RequestMapping(ResourcePath.LOGIN)
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpSession session) {
		LOGGER.info("login");
		
		if(session!=null && session.getAttribute("userLogged") != null) {
			LOGGER.info("user logged");
			UserLoggedDtoWrapper userLogged = (UserLoggedDtoWrapper) session.getAttribute("userLogged");
			if (userLogged != null) {
				LOGGER.info("user: "+userLogged.getLogin());
			}
		    return "redirect:/";
		}

		return "login";
	}
	
	@RequestMapping(value = ResourcePath.Login.DO_LOGIN, method = RequestMethod.POST)
	public ResponseEntity<?> doLogin(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session) {
		LOGGER.info("doLogin");
		
		LoginDtoWrapper loginDtoWrapper = new LoginDtoWrapper(login, password);
		UserLoggedDtoWrapper userLoggedWrapper = null;
		try {
			userLoggedWrapper = loginService.login(loginDtoWrapper);
		} catch (BusinessException be) {
			if (be.getErrors() != null && be.getErrors().getErrors() != null && !be.getErrors().getErrors().isEmpty()) {
				for (Error error : be.getErrors().getErrors()) {
					System.out.println("erro: "+error.getMessage());
				}
			}
			return ResponseEntity.status(422).body(be.getErrors().getErrors());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(e.getMessage());
		}
		if(userLoggedWrapper != null && userLoggedWrapper.hasPermissionCustomer()) {
			session.setAttribute("userLogged", userLoggedWrapper);
		}
		return ResponseEntity.ok("");
	}

}
