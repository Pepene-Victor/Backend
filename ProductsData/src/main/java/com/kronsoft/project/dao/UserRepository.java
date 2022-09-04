package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
