package com.nextneo.system.service.repository;

import java.util.List;
import java.util.Set;

import com.nextneo.system.models.entity.User;

public interface UserRepositoryCustom {

	public User addUser(User user);

	public User updateUser(User user);

	public User findById(long id);

	public List<User> findById(Set<Long> ids);

	public User findByUsername(String nome);
	
	public User findByLoginUser(User userFind);
	
	public boolean existsUserByLogin(String userName);

}
