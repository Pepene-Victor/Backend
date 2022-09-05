package com.kronsoft.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kronsoft.project.dao.ProductRepository;
import com.kronsoft.project.entities.Product;

@Service
public class ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@PostConstruct
	private void populateProductList() {
		
		try {
			if(productRepository.count() == 0) {
				
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema)
						.readValues(resource.getInputStream());
				List<Product> products = productRepository.saveAll(iterator.readAll());
				System.out.println(products);
				
			}
			
		} catch(IOException e){
			
			logger.error("An error occurred while populating products table.", e);
			
		}
		
	}
	
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}
	
	public Optional<Product> getProductById(String id) {
		
		return productRepository.findById(id);
		
	}
	
	public List<Product> getProductByName(String productName) {
		
		return productRepository.findByProductName(productName);
		
	}
	
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
		
	}
	
	public void deleteProductById(String id) {
		
		productRepository.deleteById(id);
		
	}
}