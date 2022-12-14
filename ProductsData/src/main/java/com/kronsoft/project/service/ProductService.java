package com.kronsoft.project.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kronsoft.project.dao.ProductRepository;
import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.exceptions.ProductExistsByIdException;
import com.kronsoft.project.exceptions.ProductIdNotExistException;

@Service
@Transactional
public class ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	public void populateProductTable() {
		
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
	
	public List<ProductDto> getAllProductsDto() {
		
		return productRepository.findAll().stream() 
				.map(this::convertToProductDto).toList();
		
	}
	
	public ProductDto getProductDtoById(String id) throws ProductIdNotExistException {

		if(productRepository.findById(id).isPresent()) {
			Product product = productRepository.findById(id).get();
			return convertToProductDto(product);
		}else 
			throw new ProductIdNotExistException(id);
		
	}	
	
	public ProductDto createProductDto(ProductDto productDto) throws ProductExistsByIdException {
		if(productRepository.findById(productDto.getPzn()).isPresent())
			throw new ProductExistsByIdException(productDto.getPzn());
		else {
			Product product = convertToProduct(productDto);
			
			return convertToProductDto(productRepository.save(product));
		}
		
	}
	
	public ProductDto saveProductDto(ProductDto productDto) throws ProductIdNotExistException {
		if(!productRepository.existsById(productDto.getPzn()))
			throw new ProductIdNotExistException(productDto.getPzn());
		else {
			Product product = convertToProduct(productDto);
			
			return convertToProductDto(productRepository.save(product));
		}
	}
	
	public void deleteProductById(String id) throws ProductIdNotExistException {
		
		if(!productRepository.existsById(id))
			throw new ProductIdNotExistException(id);
		productRepository.deleteById(id);
		
	}
	
	private ProductDto convertToProductDto(Product product) {
		
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		
		return productDto;
	}

	private Product convertToProduct(ProductDto productDto) {
		
		Product product = new Product();
		
		BeanUtils.copyProperties(productDto, product);
		
		return product;
	}
}
