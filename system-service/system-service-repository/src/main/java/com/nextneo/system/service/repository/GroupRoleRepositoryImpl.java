package com.nextneo.system.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nextneo.system.models.entity.GroupRole;

public class GroupRoleRepositoryImpl implements GroupRoleRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public GroupRole addGroupRole(GroupRole groupRole) {
		em.persist(groupRole);
		return groupRole;
	}
	
    public GroupRole updateGroupRole(GroupRole groupRole) {
    	em.merge(groupRole);
        return groupRole;
    }
	
	public GroupRole findByName(String name){
		
		TypedQuery<GroupRole>  query = em.createQuery("SELECT new GroupRole(g) from GroupRole g where g.name = :pName ", GroupRole.class);
		query.setParameter("pName", name);
			
		GroupRole groupRole = null;
		try {
			groupRole = query.getSingleResult();
		} catch (NoResultException nre) {
			/* NoResultException */
		}

		return groupRole;
	}
}
