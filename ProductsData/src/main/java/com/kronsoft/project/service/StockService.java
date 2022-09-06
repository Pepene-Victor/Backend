package com.kronsoft.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.StockRepository;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired 
	private ProductService productService;
	
	public List<Stock> getAllStocks(){
		
		return stockRepository.findAll();
		
	}
	
	public Stock saveStock(Stock stock) {
		
		return stockRepository.save(stock);
		
		
	}
	
	public Stock createStockByProductId(Stock stock, String productId) {
		
		Optional<Product> product = productService.getProductById(productId);
		
		stock.setProduct(product.get());
		
		return stockRepository.save(stock);
		
		
	}
	
	public List<Stock> getStockByProductName(String productName){
		
		return stockRepository.findByProductProductName(productName);
				
	}
	
	public Stock getStockByProductId(String pzn){
		
		return stockRepository.findByProductPzn(pzn);
				
	}
	
}
