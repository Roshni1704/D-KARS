package com.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.models.User;

public interface CustomerRepository extends JpaRepository<User, Integer> {

	
	User findByName(String username);

}
