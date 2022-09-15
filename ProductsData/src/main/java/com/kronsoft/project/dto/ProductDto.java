package com.kronsoft.project.dto;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.entities.Product;

public class ProductDto {


	private String pzn;

	private String supplier;

	private String productName;
	
	private String strength;
	
	private String packageSize;
	
	private String unit;
	
	

	public ProductDto() {
		super();
	}
	
	public ProductDto(Product product) {
		
		BeanUtils.copyProperties(product, this, "stock");
		
	}

	public String getPzn() {
		return pzn;
	}

	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "ProductDto [pzn=" + pzn + ", supplier=" + supplier + ", productName=" + productName + ", strength="
				+ strength + ", packageSize=" + packageSize + ", unit=" + unit + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(packageSize, productName, pzn, strength, supplier, unit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		return Objects.equals(packageSize, other.packageSize) && Objects.equals(productName, other.productName)
				&& Objects.equals(pzn, other.pzn) && Objects.equals(strength, other.strength)
				&& Objects.equals(supplier, other.supplier) && Objects.equals(unit, other.unit);
	}
	
	
	
	
}
