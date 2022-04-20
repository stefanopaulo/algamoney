package com.stefano.api.awm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefano.api.awm.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

}
