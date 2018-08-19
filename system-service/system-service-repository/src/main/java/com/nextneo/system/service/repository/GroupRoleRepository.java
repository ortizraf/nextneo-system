package com.nextneo.system.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextneo.system.models.entity.GroupRole;

public interface GroupRoleRepository extends JpaRepository<GroupRole, Long>, GroupRoleRepositoryCustom {

}