package store.business.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import store.business.domain.Product;
import store.business.domain.Supplier;
import store.persistence.MySqlProductDAO;
import store.persistence.MySqlSupplierDAO;
import store.presentation.util.JSFUtil;

@ManagedBean(name = "beanProduct") // managed beans são beans Java gerenciáveis pelo JSF 
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
		try {
			MySqlProductDAO pDAO = new MySqlProductDAO();
			this.products = new ArrayList<Product>(pDAO.listProducts());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initProduct(){
		try {
			this.product = new Product();
			this.product.setSupplier(new Supplier());
			MySqlSupplierDAO pDAO = new MySqlSupplierDAO();
			this.comboSuppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertProduct(){
		
		try {
			MySqlProductDAO pDAO = new MySqlProductDAO();
			pDAO.insertProduct(product);
			this.products = new ArrayList<Product>(pDAO.listProducts());
			JSFUtil.createSuccessMessage("Success!");	
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createErrorMessage("Error!");
		}
		
	}
	
	public void removeProduct(){
		
		try {
			MySqlProductDAO pDAO = new MySqlProductDAO();
			pDAO.deleteProduct(product);
			this.products = new ArrayList<Product>(pDAO.listProducts());
			JSFUtil.createSuccessMessage("Success!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createErrorMessage("Error!");
		}
		
	}
	
	public void updateProduct(){
		
		try {
			MySqlProductDAO pDAO = new MySqlProductDAO();
			pDAO.updateProduct(product);	
			this.products = new ArrayList<Product>(pDAO.listProducts());
			JSFUtil.createSuccessMessage("Success!");	
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createErrorMessage("Error!");
		}
		
	}
	
}
