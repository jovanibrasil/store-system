package com.store.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.store.DAO.ProductDAO;
import com.store.DAO.SupplierDAO;
import com.store.domain.Product;
import com.store.domain.Supplier;
import com.store.util.JSFUtil;

@ManagedBean(name = "beanProduct")
@ViewScoped
public class ProductBean {

	private ArrayList<Product> products;
	private Product product;
	private ArrayList<Product> filteredProducts;
	private ArrayList<Supplier> comboSuppliers;

	public ArrayList<Supplier> getComboSuppliers() {
		return comboSuppliers;
	}

	public void setComboSuppliers(ArrayList<Supplier> comboSuppliers) {
		this.comboSuppliers = comboSuppliers;
	}

	public ArrayList<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(ArrayList<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product Product) {
		this.product = Product;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> Products) {
		this.products = Products;
	}
	
	@PostConstruct // executa antes da pasta ser visualizada
	public void populateProductsList() {
		
		ProductDAO pDAO = new ProductDAO();
		this.products = new ArrayList<Product>(pDAO.listProducts());
		
	}
	
	public void initProduct(){
		this.product = new Product();
		this.product.setSupplier(new Supplier());
		
		SupplierDAO pDAO = new SupplierDAO();
		this.comboSuppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
		
	}
	
	public void insertProduct(){
		
		ProductDAO pDAO = new ProductDAO();
		if(pDAO.insertProduct(product))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.products = new ArrayList<Product>(pDAO.listProducts());
		
	}
	
	public void removeProduct(){
		
		ProductDAO pDAO = new ProductDAO();
		if(pDAO.deleteProduct(product))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.products = new ArrayList<Product>(pDAO.listProducts());
		
	}
	
	public void updateProduct(){
		
		ProductDAO pDAO = new ProductDAO();
		if(pDAO.updateProduct(product))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.products = new ArrayList<Product>(pDAO.listProducts());
	
	}
	
}
