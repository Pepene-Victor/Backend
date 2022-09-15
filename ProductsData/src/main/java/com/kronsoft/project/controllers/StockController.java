package com.kronsoft.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getAllStocks(){
		
		return stockService.getAllStocks();
	}
	
	@GetMapping(value = "/productId", produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto getStockByProductId(@RequestParam String productId) {
		
		return stockService.getStockByProductId(productId);
		
	}
	
	@PostMapping(value = "/create/productId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto createStock(@RequestBody StockDto stockDto, @RequestParam String productId) {
		
		return stockService.createStockByProductId(stockDto, productId);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto updateStock(@RequestBody StockDto stockDto) {
		
		return stockService.saveStock(stockDto);
	}
	
	
}
