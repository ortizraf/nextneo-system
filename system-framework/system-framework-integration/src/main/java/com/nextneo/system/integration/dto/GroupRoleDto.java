package com.nextneo.system.integration.dto;

import java.util.Set;

public class GroupRoleDto  {
	
	private Long id;
	
	private String name;
	
    private Set<UserDto> users;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}


}
