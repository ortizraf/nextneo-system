package com.nextneo.system.service.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextneo.system.service.jms.JMSProducer;

/**
* @author  Rafael M Ortiz
*/
@Service
public class HomeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeService.class);
	
	@Autowired
	private JMSProducer jmsProducer;
		
	public String testJMS() {
		LOGGER.info("content");
		
		try {
			jmsProducer.send("queue.test", "teste");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "online";
		
	}

}
