package com.store.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store.domain.Product;
import com.store.domain.Supplier;
import com.store.factory.ConnectionFactory;

public class ProductDAO {

	private Connection connection = null;
	
	public ProductDAO() {
		try {
			this.connection = ConnectionFactory.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertProduct(Product product) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO products ");
		sql.append("(description, quantity, price, supplier_id) ");
		sql.append("VALUES (?, ?, ?, ?)");

		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, product.getDescription());
			ps.setInt(2, product.getQuantity());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getSupplier().getSupplierId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean deleteProduct(Product product) {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM products ");
		sql.append("WHERE product_id = ? ");

		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, product.getProductId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateProduct(Product product) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE products ");
		sql.append("SET description = ?, quantity = ?, price = ?, supplier_id = ? ");
		sql.append("WHERE product_id = ? ");

		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, product.getDescription());
			ps.setInt(2, product.getQuantity());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getSupplier().getSupplierId());
			ps.setInt(5, product.getProductId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Product selectProductById(int id) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT prod.product_id, prod.description, prod.quantity,"
				+ " prod.price, prod.supplier_id, sup.description"
				+ " FROM products prod"
				+ " INNER JOIN suppliers sup"
				+ " ON prod.supplier_id = sup.supplier_id ");
		sql.append("WHERE product_id = ? ");

		try {

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			Product p = null;

			if (result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				p = new Product();
				Supplier sup = new Supplier();

				p.setDescription(result.getString("prod.description"));
				p.setQuantity(result.getInt("prod.quantity"));
				p.setPrice(result.getDouble("prod.price"));
				sup.setDescription(result.getString("sup.description"));
				sup.setSupplierId(result.getInt("sup.supplier_id"));
				p.setSupplier(sup);
				
				return p;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Product> listProducts() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT prod.product_id, prod.description, prod.quantity,"
				+ " prod.price, prod.supplier_id, sup.description"
				+ " FROM products prod"
				+ " INNER JOIN suppliers sup"
				+ " ON prod.supplier_id = sup.supplier_id");
		
		try {

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet result = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();

			while (result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				Product p = new Product();
				Supplier sup = new Supplier();

				p.setproductId(result.getInt("prod.product_id"));
				p.setDescription(result.getString("prod.description"));
				p.setQuantity(result.getInt("prod.quantity"));
				p.setPrice(result.getDouble("prod.price"));
				sup.setDescription(result.getString("sup.description"));
				sup.setSupplierId(result.getInt("prod.supplier_id"));
				p.setSupplier(sup);
				
				products.add(p);
			}

			return products;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
