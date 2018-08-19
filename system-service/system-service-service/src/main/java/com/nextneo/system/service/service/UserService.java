package com.nextneo.system.service.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nextneo.system.models.entity.GroupRole;
import com.nextneo.system.models.entity.User;
import com.nextneo.system.models.enums.UserType;
import com.nextneo.system.service.message.MessageSpringProperties;
import com.nextneo.system.service.repository.UserRepository;
import com.nextneo.system.utils.crypto.Encryptor;
import com.nextneo.system.utils.errors.Errors;
import com.nextneo.system.utils.exception.BusinessException;
import com.nextneo.system.utils.messages.MessageProperties;

/**
* @author  Rafael M Ortiz
*/
@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private GroupRoleService groupRoleService;
	
	@Autowired
	private MessageSpringProperties messageSpringProperties;
	
	private void consist(User user) throws BusinessException {
		Errors errors = new Errors();
		
		if (user.getId() == null) {
			if (user.getLogin() == null) {
				throw new BusinessException("invalid data", errors);
			}
			if (user.getType() == null) {
				user.setType(UserType.CUSTOMER);
			}
			if (user.getPassword() == null) {
				user.generatePassword();
				LOGGER.info(Encryptor.decrypt(user.getPassword()));
			}
			
			GroupRole groupRole = groupRoleService.findByName(user.getType().toString());
			if (groupRole.getUsers() == null) {
				groupRole.setUsers(new HashSet<>());
			}
			groupRole.getUsers().add(user);
			user.setGroupRoles(new HashSet<>());
			user.getGroupRoles().add(groupRole);
		}
		
		boolean existsDocument = repository.existsUserByLogin(user.getLogin());
		if (existsDocument) {
			errors.addError(MessageProperties.getMessage("login_exists"));
			throw new BusinessException(messageSpringProperties.getMessage("login_exists"), errors);
		}
		if(errors.hasErrors()) {
			throw new BusinessException("invalid data", errors);
		}
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User addUser(User user) throws BusinessException, Exception  {
		LOGGER.info(" addUser ");
		if (user.getId() != null) {
			throw new BusinessException(messageSpringProperties.getMessage("use_update_method"), null);
		}
		consist(user);

		return repository.addUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User updateUser(User user) throws BusinessException, Exception  {
		LOGGER.info(" updateUser ");
		if (user.getId() == null) {
			throw new BusinessException(messageSpringProperties.getMessage("user_id_required"), null);
		}

		return repository.updateUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User findById(long id) throws BusinessException, Exception {
		LOGGER.info(" findById ");

		User user = repository.findById(id);
		if (user == null || user.getId() == null) {
			throw new BusinessException(messageSpringProperties.getMessage("user_not_found"), null);
		}
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findById(Set<Long> usersId) throws BusinessException, Exception {
		LOGGER.info(" findById ");

		List<User> users = repository.findById(usersId);
		if (users == null || users.isEmpty()) {
			throw new BusinessException(messageSpringProperties.getMessage("user_not_found"), null);
		}
		return users;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User findByUsername(String username) throws BusinessException, Exception {
		LOGGER.info("findByUsername");

		User user = repository.findByUsername(username);
		if (user == null || user.getId() == null) {
			throw new BusinessException(messageSpringProperties.getMessage("user_not_found"), null);
		}

		return user;

	}

}
