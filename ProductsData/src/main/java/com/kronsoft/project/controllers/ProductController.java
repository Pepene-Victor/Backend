package com.kronsoft.project.controllers;

import java.util.List;

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

import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.exceptions.ProductIdNotExistException;
import com.kronsoft.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAllProducts(){
		
		return productService.getAllProductsDto();
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto getProductById(@PathVariable String id) {
		
		return productService.getProductDtoById(id);
		
	}
	
	@GetMapping(value = "/productName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getProductByProductName(@RequestParam String productName) {
		
		return productService.getProductDtoByName(productName);
		
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto createProduct(@Valid @RequestBody ProductDto product) {
		
		return productService.saveProductDto(product);
		
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto updateProduct(@RequestBody ProductDto product) {
		
		return productService.saveProductDto(product);
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteProduct(@PathVariable String id) throws ProductIdNotExistException {
		
		productService.deleteProductById(id);
		
	}
	
}
