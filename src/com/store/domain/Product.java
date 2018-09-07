package com.store.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id @GeneratedValue
	@Column(name="product_id")
	private Integer productId;
	@Column(name="description")
	private String description;
	@Column(name="quantity")
	private Integer quantity;
	@Column(name="price")
	private double price;
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setproductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public String toString() {
		return this.productId + " - " + 
				this.description + " - " +
				this.price + " - " + this.quantity;
	}
	
}
