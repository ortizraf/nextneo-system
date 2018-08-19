package com.nextneo.system.integration.dto.wrapper;

import java.util.Calendar;
import java.util.Set;

import com.nextneo.system.integration.dto.GroupRoleDto;
import com.nextneo.system.integration.dto.enums.UserTypeDto;

public class UserLoggedDtoWrapper {

	private Calendar lastAccess;
	
	private String login;
	
	private String password;
	
	private UserTypeDto type;

    private Set<GroupRoleDto> groupRoles;

	public Calendar getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Calendar lastAccess) {
		this.lastAccess = lastAccess;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserTypeDto getType() {
		return type;
	}

	public void setType(UserTypeDto type) {
		this.type = type;
	}

	public Set<GroupRoleDto> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(Set<GroupRoleDto> groupRoles) {
		this.groupRoles = groupRoles;
	}
	
	public UserLoggedDtoWrapper() {
		super();
	}
	
	public boolean hasPermissionCustomer() {
		if (this.groupRoles != null && !this.groupRoles.isEmpty()) {
			for (GroupRoleDto group : this.groupRoles) {
				if (group.getName() != null && group.getName().equalsIgnoreCase(UserTypeDto.CUSTOMER.name())) {
					return true;
				}
			}
		}		
		return false;
	}

}
