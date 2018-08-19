package com.nextneo.system.service.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.system.models.entity.User;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User addUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		em.merge(user);
		return user;
	}

	@Override
	public User findById(long id) {

		TypedQuery<User> query = em.createQuery("SELECT new User(u) from User u WHERE u.id = :pId ", User.class);
		query.setParameter("pId", id);

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return user;
	}

	@Override
	public List<User> findById(Set<Long> ids) {

		TypedQuery<User> query = em.createQuery("SELECT new User(u) from User u WHERE u.id in :pIds ", User.class);
		query.setParameter("pIds", ids);

		List<User> userList = query.getResultList();
		return userList;
	}

	@Override
	public User findByUsername(String username) {

		TypedQuery<User> query = em.createQuery("SELECT new User(u) from User u WHERE u.login = :pUsername ",
				User.class);
		query.setParameter("pUsername", username);

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return user;
	}

	@Override
	public User findByLoginUser(User userFind) {

		TypedQuery<User> query = em.createQuery("SELECT new User(u) from User u WHERE u.login = :pLogin and u.password = :pPassword", User.class);
		query.setParameter("pLogin", userFind.getLogin());
		query.setParameter("pPassword", userFind.getPassword());

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return user;
	}
	
	@Override
	public boolean existsUserByLogin(String login) {
		StringBuilder queryS = new StringBuilder();
		queryS.append("SELECT count(u) from User u WHERE u.login = :pLogin ");
		
		TypedQuery<Long>  query = em.createQuery(queryS.toString(), Long.class);
		query.setParameter("pLogin", login);
		
		long count = query.getSingleResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}
