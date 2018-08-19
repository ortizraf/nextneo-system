package com.nextneo.system.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nextneo.system.models.entity.GroupRole;
import com.nextneo.system.service.repository.GroupRoleRepository;

@Service
public class GroupRoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupRoleService.class);
	
	@Autowired
	private GroupRoleRepository repository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public GroupRole findByName(String name){
		LOGGER.info(" findByName ");
		
		return repository.findByName(name);
	}

}
