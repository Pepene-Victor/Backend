package com.kronsoft.project.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Product {

	@Id
	@NotNull
	@Size(min = 8, max = 8)
	private String pzn;
	
	@Column(name = "supplier")
	@Size(max = 100)
	private String supplier;
	
	@Column(name = "product_name", nullable = false)
	@NotNull
	@Size(max = 100)
	private String productName;
	
	@Column(name = "strength", nullable = false)
	@NotNull
	@Size(max = 100)
	private String strength;
	
	@Column(name = "package_size", nullable = false)
	@NotNull
	@Size(max = 20)
	private String packageSize;
	
	@Column(name = "unit", nullable = false)
	@NotNull
	@Size(max = 2)
	private String unit;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
	private Stock stock;
	

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
		return "Product [pzn=" + pzn + ", supplier=" + supplier + ", productName=" + productName + ", strength="
				+ strength + ", packageSize=" + packageSize + ", unit=" + unit + ", stock=" + stock + "]";
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
		Product other = (Product) obj;
		return Objects.equals(packageSize, other.packageSize) && Objects.equals(productName, other.productName)
				&& Objects.equals(pzn, other.pzn) && Objects.equals(strength, other.strength)
				&& Objects.equals(supplier, other.supplier) && Objects.equals(unit, other.unit);
	}
	
	
	
	
	
	
}
