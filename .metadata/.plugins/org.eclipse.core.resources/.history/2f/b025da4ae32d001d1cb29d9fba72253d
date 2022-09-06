package com.kronsoft.project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;
import com.kronsoft.project.service.ProductService;
import com.kronsoft.project.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Stock> getAllStocks(){
		
		return stockService.getAllStocks();
	}
	
	@GetMapping(value = "/productId", produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock getStockByProductId(@RequestParam String productId) {
		
		return stockService.getStockByProductId(productId);
		
	}
	
	@GetMapping(value = "/productName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Stock> getStockByProductName(@RequestParam String productName) {
		
		return stockService.getStockByProductName(productName);
		
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock createStock(@RequestBody Stock stock) {
		
		return stockService.saveStock(stock);
	}
	
	@PostMapping(value = "/create/productId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock createStock(@RequestBody Stock stock, @RequestParam String productId) {
		
		Optional<Product> product = productService.getProductById(productId);
		
		stock.setProduct(product.get());
		
		return stockService.saveStock(stock);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock updateStock(@RequestBody Stock stock) {
		
		return stockService.saveStock(stock);
	}
	
	
}
