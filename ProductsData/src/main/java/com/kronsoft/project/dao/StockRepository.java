package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kronsoft.project.entities.Stock;


public interface StockRepository extends JpaRepository<Stock, Long>{

}
