package com.kronsoft.project.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.ProductRepository;
import com.kronsoft.project.dao.StockRepository;
import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;

@Service
@Transactional
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired 
	private ProductRepository productRepository;
	
	public void createStockForProduct() {
		
		List<Product> products = productRepository.findAll();
		if(stockRepository.count() == 0) {
			
			for(Product product: products) {
				
				Random r = new Random();
				Stock stock = new Stock();
				stock.setPrice(new BigDecimal(Math.random()));
				Long quantity = r.nextLong();
				if(quantity < 1)
					quantity = quantity * (-1);
				stock.setQuantity(quantity);
				stock.setProduct(product);
				stockRepository.save(stock);
				
			}
			
			System.out.println(stockRepository.findAll().stream() 
					.map(this::convertToStockDto).toList());
			
		}
	}

	
	public List<StockDto> getAllStocks(){
		
		return stockRepository.findAll().stream() 
				.map(this::convertToStockDto).toList();
	}
	
	public StockDto getStockByProductId(String pzn) {
		
		return convertToStockDto(stockRepository.findByProductPzn(pzn));
	}
	
	public StockDto createStockByProductId(StockDto stockDto, String productId) {
		
		Stock stock = convertToStock(stockDto);
		productRepository.findById(productId).ifPresentOrElse(product ->
		{stock.setProduct(product);},() -> new Exception("Product with pzn "+ productId +" not found " ));
		;
		return convertToStockDto(this.stockRepository.save(stock));
	}
	
	public StockDto updateStockByProductId(StockDto stockDto) {
		
		Stock stock = this.stockRepository.findById(stockDto.getId()).get();
		BeanUtils.copyProperties(stockDto, stock);
		return convertToStockDto(this.stockRepository.save(stock));
		
	}

	private StockDto convertToStockDto(Stock stock) {
		
		StockDto stockDto = new StockDto();
		BeanUtils.copyProperties(stock, stockDto);
		
		return stockDto;
	}

	private Stock convertToStock(StockDto stockDto) {
		
		Stock stock = new Stock();
		BeanUtils.copyProperties(stockDto, stock);
		
		return stock;
	}
	
}
