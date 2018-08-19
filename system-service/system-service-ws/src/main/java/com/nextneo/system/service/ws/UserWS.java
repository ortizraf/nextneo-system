package com.nextneo.system.service.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextneo.system.integration.dto.UserDto;
import com.nextneo.system.models.entity.User;
import com.nextneo.system.service.service.UserService;
import com.nextneo.system.service.service.mapper.UserMapper;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;

/**
* @author  Rafael M Ortiz
*/
@RestController
@RequestMapping(ResourcePath.USER)
public class UserWS {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserWS.class.getName());

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = ResourcePath.User.FIND_BY_ID, method = RequestMethod.GET)
	@ResponseBody
	public UserDto findById(@PathVariable("id") Long id) throws BusinessException, Exception {
		LOGGER.info("findById: " + id);

		User user = userService.findById(id);
		LOGGER.info(user.getId().toString());
		UserDto userDto = userMapper.userToUserDto(user);

		return userDto;
	}

	@RequestMapping(value = ResourcePath.User.FIND_BY_USERNAME, method = RequestMethod.GET)
	@ResponseBody
	public UserDto findByUsername(@PathVariable("username") String username) throws BusinessException, Exception {
		LOGGER.info("findByUsername: " + username);

		User user = userService.findByUsername(username);
		LOGGER.info(user.getId().toString());
		UserDto userDto = userMapper.userToUserDto(user);

		return userDto;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public UserDto add(@RequestBody UserDto userDto) throws BusinessException, Exception {
		LOGGER.info("add");
		
		User user = userMapper.userDtoToUser(userDto);
		user = userService.addUser(user);
		
		userDto = userMapper.userToUserDto(user);
		return userDto;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public UserDto update(@RequestBody UserDto userDto) throws BusinessException, Exception {
		LOGGER.info("update");
		
		User user = userMapper.userDtoToUser(userDto);
		user = userService.updateUser(user);
		
		userDto = userMapper.userToUserDto(user);
		return userDto;
	}

}
