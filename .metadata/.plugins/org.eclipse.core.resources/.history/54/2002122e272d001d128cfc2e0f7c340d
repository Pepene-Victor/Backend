package com.kronsoft.project.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.entities.Product;
import com.kronsoft.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts(){
		
		return productService.getAllProducts();
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional <Product> getProductById(@PathVariable String id) {
		
		return productService.getProductById(id);
		
	}
	
	@GetMapping(value = "/productName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductByProductName(@RequestParam String productName) {
		
		return productService.getProductByName(productName);
		
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@Valid @RequestBody Product product) {
		
		return productService.saveProduct(product);
		
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@RequestBody Product product) {
		
		return productService.saveProduct(product);
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteProduct(@PathVariable String id) {
		
		productService.deleteProductById(id);
		
	}
	
}
