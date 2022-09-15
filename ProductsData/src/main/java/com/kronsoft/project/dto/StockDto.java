package com.kronsoft.project.dto;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.entities.Stock;

public class StockDto {


	private Long id;
	
	private Long quantity;
	
	private BigDecimal price;

	public StockDto() {
		
	}
	
	public StockDto(Stock stock) {
		
		BeanUtils.copyProperties(stock, this, "product");
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockDto [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDto other = (StockDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity);
	}
	
	
}
