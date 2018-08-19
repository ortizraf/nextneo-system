package com.nextneo.system.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextneo.system.models.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}