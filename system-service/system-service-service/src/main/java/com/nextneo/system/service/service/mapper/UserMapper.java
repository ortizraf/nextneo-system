package com.nextneo.system.service.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nextneo.system.integration.dto.GroupRoleDto;
import com.nextneo.system.integration.dto.UserDto;
import com.nextneo.system.integration.dto.enums.UserTypeDto;
import com.nextneo.system.models.entity.GroupRole;
import com.nextneo.system.models.entity.User;

/**
* @author  Rafael M Ortiz
*/
@Component
public class UserMapper {

	@Autowired
	private GroupRoleMapper groupRoleMapper;

	public UserDto userToUserDto(User user) {
		UserDto userDto = null;
		if (user != null) {
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setLogin(user.getLogin());
			userDto.setPassword(user.getPassword());
			if (user.getType() != null) {
				userDto.setType(UserTypeDto.valueOf(user.getType().name()));
			}
			if (user.getGroupRoles() != null && !user.getGroupRoles().isEmpty()) {
				Set<GroupRoleDto> groupRolesDto = groupRoleMapper.groupRoleListToGroupRoleDtoList(user.getGroupRoles());
				userDto.setGroupRoles(groupRolesDto);
			}
		}
		return userDto;
	}

	public User userDtoToUser(UserDto userDto) {
		User user = null;
		if (userDto != null) {
			user = new User();
			user.setId(userDto.getId());
			user.setLogin(userDto.getLogin());
			user.setPassword(userDto.getPassword());
			if (userDto.getGroupRoles() != null && !userDto.getGroupRoles().isEmpty()) {
				Set<GroupRole> groupRoles = groupRoleMapper.groupRoleDtoListToGroupRoleList(userDto.getGroupRoles());
				user.setGroupRoles(groupRoles);
			}
		}
		return user;
	}

	public List<UserDto> userListToUserDtoList(List<User> userList) {
		List<UserDto> userListDto = null;
		if (userList != null && !userList.isEmpty()) {
			userListDto = new ArrayList<>();
			for (User user : userList) {
				UserDto userDto = userToUserDto(user);
				userListDto.add(userDto);
			}
		}
		return userListDto;
	}

	public List<User> userDtoListToUserList(List<UserDto> userDtoList) {
		List<User> userList = null;
		if (userDtoList != null && !userDtoList.isEmpty()) {
			userList = new ArrayList<>();
			for (UserDto userDto : userDtoList) {
				User user = userDtoToUser(userDto);
				userList.add(user);
			}
		}
		return userList;
	}

}
