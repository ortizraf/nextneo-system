package com.nextneo.system.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author  Rafael M Ortiz
*/
@Controller
public class HomeController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String index(HttpSession session){
		LOGGER.info("home");
		
		if(session==null || session.getAttribute("userLogged") == null) {
		    return "redirect:login";
		}

	    return "home";
    }

}