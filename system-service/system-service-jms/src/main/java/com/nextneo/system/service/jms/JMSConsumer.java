package com.nextneo.system.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.nextneo.system.integration.dto.UserDto;

/**
* @author  Rafael M Ortiz
*/
@Component
public class JMSConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSConsumer.class);

	@JmsListener(destination = "queue.test")
	public void receive(String message) {
		LOGGER.info("received message='{}'", message);
		System.out.println("received message: "+ message);
	}
	
	@JmsListener(destination = "queue.test")
	public void receive(UserDto userDto) {
		LOGGER.info("received object'{}'", userDto.getId());
		System.out.println("received object: "+ userDto.getId());
	}

}
