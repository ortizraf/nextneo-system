package com.nextneo.system.service.ws;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nextneo.system.service.message.MessageSpringProperties;
import com.nextneo.system.service.service.HomeService;

/**
* @author  Rafael M Ortiz
*/
@Controller
public class HomeController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
    @Resource
    private Environment env;
	
	@Autowired
	private MessageSpringProperties messageSpringProperties;
	
	@Autowired
	private HomeService homeService;

    @RequestMapping("/")
    public String index(Locale locale){
		LOGGER.info("index");
		
		System.out.println(env.getProperty("app"));
		
        String welcome = messageSpringProperties.getMessage("field.required");
		//String welcome = MessageProperties.getMessage("field.required");
		LOGGER.info(welcome);
		
		// JMS Test
		homeService.testJMS();

        return "index";
    }

}
