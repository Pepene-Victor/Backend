package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.Product;



public interface ProductRepository extends JpaRepository<Product, String>{

	
	
}
