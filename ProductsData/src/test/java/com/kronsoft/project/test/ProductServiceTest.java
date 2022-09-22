package com.kronsoft.project.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.exceptions.ProductExistsByIdException;
import com.kronsoft.project.exceptions.ProductIdNotExistException;
import com.kronsoft.project.service.ProductService;
import com.kronsoft.project.service.StockService;

@SpringBootTest
@ContextConfiguration
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {
	//product test values
	public static final String PZN = "80808080";
	public static final String SUPPLIER = "Express Med";
	public static final String PRODUCT_NAME = "PARACETAMOL";
	public static final String STRENGTH = "10 mg";
	public static final String PACKAGE_SIZE = "20";
	public static final String UNIT = "ST";
	
	public static final String SUPPLIER_2 = "Henning";
	public static final String PRODUCT_NAME_2 = "L-THYROX";
	public static final String STRENGTH_2 = "100 mg";
	public static final String PACKAGE_SIZE_2 = "10";
	public static final String UNIT_2 = "ml";
	//stock test values
	public static final Long QUANTITY = 200L;
	public static final BigDecimal PRICE = new BigDecimal(35.99);
	
	public static final Long QUANTITY_2 = 100L;
	public static final BigDecimal PRICE_2 = new BigDecimal(99.99);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private StockService stockService;
	
	private ProductDto persistedProduct;
	private StockDto persistedStock;
	private String persistedSupplier;
	private String persistedProductName;
	private String persistedStrength;
	private String persistedPackageSize;
	private String persistedUnit;
	public BigDecimal persistedPrice;
	public Long persistedQuantity;
	
	@BeforeAll
	public void createProductTest() throws ProductExistsByIdException {
		ProductDto product = new ProductDto();
		product.setPzn(PZN);
		product.setSupplier(SUPPLIER);
		product.setProductName(PRODUCT_NAME);
		product.setStrength(STRENGTH);
		product.setPackageSize(PACKAGE_SIZE);
		product.setUnit(UNIT);
		
		persistedProduct = productService.createProductDto(product);
		Assertions.assertNotNull(persistedProduct);
		updatePersistedProductFields();
		createStockForProduct();
		
	}
	private void updatePersistedProductFields() {
		persistedSupplier = persistedProduct.getSupplier();
		persistedProductName = persistedProduct.getProductName();
		persistedStrength = persistedProduct.getStrength();
		persistedPackageSize = persistedProduct.getPackageSize();
		persistedUnit = persistedProduct.getUnit();
		
	}
	
	public void createStockForProduct(){
		StockDto stock = new StockDto();
		stock.setPrice(PRICE);
		stock.setQuantity(QUANTITY);
		
		persistedStock = stockService.createStockByProductId(stock, PZN);
		Assertions.assertNotNull(persistedStock);
		updatePersistedStockFieldsForProduct();
	}
	private void updatePersistedStockFieldsForProduct() {
		persistedPrice = persistedStock.getPrice();
		persistedQuantity = persistedStock.getQuantity();
	}
	@Nested
    @Order(1)
    class ProductServiceTests {
		
		@Test
		public void getProductById() throws ProductIdNotExistException {
			ProductDto product = productService.getProductDtoById(PZN);
			Assertions.assertEquals(PZN, product.getPzn());
			Assertions.assertEquals(persistedSupplier, product.getSupplier());
			Assertions.assertEquals(persistedProductName, product.getProductName());
			Assertions.assertEquals(persistedStrength, product.getStrength());
			Assertions.assertEquals(persistedPackageSize, product.getPackageSize());
			Assertions.assertEquals(persistedUnit, product.getUnit());
		}
		@Test
		public void editProductTest() {
			persistedProduct.setSupplier(SUPPLIER_2);
			persistedProduct.setProductName(PRODUCT_NAME_2);
			persistedProduct.setStrength(STRENGTH_2);
			persistedProduct.setPackageSize(PACKAGE_SIZE_2);
			persistedProduct.setUnit(UNIT_2);
			persistedProduct = productService.saveProductDto(persistedProduct);
			
			Assertions.assertEquals(SUPPLIER_2, persistedProduct.getSupplier());
			Assertions.assertEquals(PRODUCT_NAME_2, persistedProduct.getProductName());
			Assertions.assertEquals(STRENGTH_2, persistedProduct.getStrength());
			Assertions.assertEquals(PACKAGE_SIZE_2, persistedProduct.getPackageSize());
			Assertions.assertEquals(UNIT_2, persistedProduct.getUnit());
			
			updatePersistedProductFields();
		}

    }

    @Nested
    @Order(2)
    class StockServiceTests {

    	@Test
    	public void updateStockByProductIdTest() {
    		
    		persistedStock.setPrice(PRICE_2);
    		persistedStock.setQuantity(QUANTITY_2);
    		persistedStock = stockService.updateStockByProductId(persistedStock);
    		
    		Assertions.assertEquals(PRICE_2, persistedStock.getPrice());
    		Assertions.assertEquals(QUANTITY_2, persistedStock.getQuantity());

    		updatePersistedStockFieldsForProduct();
    	}
    	
    	@Test
    	public void getStockByProductIdTest() {
    		
    		StockDto stock = stockService.getStockByProductId(PZN);
    		
    		Assertions.assertTrue(persistedPrice.setScale(2, RoundingMode.DOWN).compareTo(stock.getPrice()) == 0);
    		Assertions.assertEquals(persistedQuantity, stock.getQuantity());
    	}
    }

	
	@AfterAll
	public void deleteProductTest() throws ProductIdNotExistException {
		ProductDto product = productService.getProductDtoById(PZN);
		productService.deleteProductById(product.getPzn());
		
		Assertions.assertThrows(ProductIdNotExistException.class, () -> {
			productService.getProductDtoById(PZN);
		});
	}

}
