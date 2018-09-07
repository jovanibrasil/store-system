package store.persistence;

import java.util.List;

import store.business.domain.Product;

public interface ProductDAO {

	public void insertProduct(Product product);
	public void deleteProduct(Product product);
	public void updateProduct(Product product);
	public Product selectProductById(int id);
	public List<Product> listProducts();

}
