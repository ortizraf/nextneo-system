package com.nextneo.system.service.service.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nextneo.system.integration.dto.GroupRoleDto;
import com.nextneo.system.models.entity.GroupRole;

/**
* @author  Rafael M Ortiz
*/
@Component
public class GroupRoleMapper {

	public GroupRoleDto groupRoleToGroupRoleDto(GroupRole groupRole) {
		GroupRoleDto groupRoleDto = null;
		if (groupRole != null) {
			groupRoleDto = new GroupRoleDto();
			groupRoleDto.setId(groupRole.getId());
			groupRoleDto.setName(groupRole.getName());
		}
		return groupRoleDto;
	}

	public GroupRole groupRoleDtoToGroupRole(GroupRoleDto groupRoleDto) {
		GroupRole groupRole = null;
		if (groupRoleDto != null) {
			groupRole = new GroupRole();
			groupRole.setId(groupRoleDto.getId());
			groupRole.setName(groupRoleDto.getName());
		}
		return groupRole;
	}

	public Set<GroupRoleDto> groupRoleListToGroupRoleDtoList(Set<GroupRole> set) {
		Set<GroupRoleDto> groupRoleListDto = null;
		if (set != null && !set.isEmpty()) {
			groupRoleListDto = new HashSet<>();
			for (GroupRole groupRole : set) {
				GroupRoleDto groupRoleDto = groupRoleToGroupRoleDto(groupRole);
				groupRoleListDto.add(groupRoleDto);
			}
		}
		return groupRoleListDto;
	}

	public Set<GroupRole> groupRoleDtoListToGroupRoleList(Set<GroupRoleDto> groupRoleDtoList) {
		Set<GroupRole> groupRoleList = null;
		if (groupRoleDtoList != null && !groupRoleDtoList.isEmpty()) {
			groupRoleList = new HashSet<>();
			for (GroupRoleDto groupRoleDto : groupRoleDtoList) {
				GroupRole groupRole = groupRoleDtoToGroupRole(groupRoleDto);
				groupRoleList.add(groupRole);
			}
		}
		return groupRoleList;
	}

}
