package com.store.DAO;

import java.util.List;
import com.store.domain.Product;

public interface ProductDAO {

	public void insertProduct(Product product);
	public void deleteProduct(Product product);
	public void updateProduct(Product product);
	public Product selectProductById(int id);
	public List<Product> listProducts();

}
