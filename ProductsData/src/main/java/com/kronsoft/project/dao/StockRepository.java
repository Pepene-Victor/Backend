package com.kronsoft.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.Stock;


public interface StockRepository extends JpaRepository<Stock, Long>{

	List<Stock> findByProductProductName(String productName);
	
	Stock findByProductPzn(String pzn);
	
}
