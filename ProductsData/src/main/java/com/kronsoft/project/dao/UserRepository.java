package com.kronsoft.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);	

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String email);

}
