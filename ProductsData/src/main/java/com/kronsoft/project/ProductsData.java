package com.kronsoft.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kronsoft.project.service.ProductService;
import com.kronsoft.project.service.StockService;

@SpringBootApplication
public class ProductsData {
	
	@Autowired 
	private ProductService productService;
	
	@Autowired 
	private StockService stockService;

	public static void main(String[] args) {
		SpringApplication.run(ProductsData.class, args);
	}
	
	@PostConstruct
	private void init() {

		productService.populateProductTable();
		stockService.createStockForProduct();
		
	}
	
}
