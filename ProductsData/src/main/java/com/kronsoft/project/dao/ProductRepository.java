package com.kronsoft.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.Product;



public interface ProductRepository extends JpaRepository<Product, String>{

	List<Product> findByProductName(String productName);
	
}
