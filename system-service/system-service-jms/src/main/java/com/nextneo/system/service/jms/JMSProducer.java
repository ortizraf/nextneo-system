package com.nextneo.system.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.nextneo.system.integration.dto.UserDto;

/**
* @author  Rafael M Ortiz
*/
@Component
public class JMSProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSProducer.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String destination, String message) {
		LOGGER.info("sending message='{}' to destination='{}'", message, destination);
		jmsTemplate.convertAndSend(destination, message);
	}
	
	public void send(String destination, UserDto userDto){
		jmsTemplate.convertAndSend(destination, userDto);
	}

}
