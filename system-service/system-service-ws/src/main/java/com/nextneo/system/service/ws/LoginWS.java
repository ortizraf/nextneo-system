package com.nextneo.system.service.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextneo.system.integration.dto.UserDto;
import com.nextneo.system.integration.dto.wrapper.LoginDtoWrapper;
import com.nextneo.system.integration.dto.wrapper.UserLoggedDtoWrapper;
import com.nextneo.system.models.entity.User;
import com.nextneo.system.service.message.MessageSpringProperties;
import com.nextneo.system.service.service.LoginService;
import com.nextneo.system.service.service.mapper.UserMapper;
import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.path.ResourcePath;

@RestController
@RequestMapping(ResourcePath.LOGIN)
public class LoginWS {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginWS.class.getName());

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MessageSpringProperties message;

	@RequestMapping(value = ResourcePath.Login.DO_LOGIN)
	@ResponseBody
	public UserLoggedDtoWrapper doLogin(@RequestBody LoginDtoWrapper loginDtoWrapper)
			throws BusinessException, Exception {
		LOGGER.info("doLogin");

		UserDto userDto = null;
		UserLoggedDtoWrapper userLoggedDtoWrapper = null;

		User user = loginService.doLogin(loginDtoWrapper);
		userDto = userMapper.userToUserDto(user);
		if (userDto == null) {
			Errors errors = new Errors();
			errors.addError(message.getMessage("user_not_found"));
			throw new BusinessException("invalid data", errors);
		}

		userLoggedDtoWrapper = new UserLoggedDtoWrapper();
		userLoggedDtoWrapper.setLogin(userDto.getLogin());
		userLoggedDtoWrapper.setPassword(userDto.getPassword());
		userLoggedDtoWrapper.setLastAccess(userDto.getLastAccess());
		userLoggedDtoWrapper.setType(userDto.getType());
		userLoggedDtoWrapper.setGroupRoles(userDto.getGroupRoles());

		return userLoggedDtoWrapper;
	}

}
