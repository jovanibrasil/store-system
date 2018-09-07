package com.store.domain;

public class Product {
	private int productId;
	private String description;
	private int quantity;
	private double price;
	private Supplier supplier;
	
	public int getProductId() {
		return productId;
	}
	
	public void setproductId(int productId) {
		this.productId = productId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
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
