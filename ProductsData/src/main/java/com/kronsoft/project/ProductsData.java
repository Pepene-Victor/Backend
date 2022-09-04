package com.kronsoft.project;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsData {

	public static void main(String[] args) {
		SpringApplication.run(ProductsData.class, args);
	}
	
	@PostConstruct
	private void init() {

		
	}
	
}
