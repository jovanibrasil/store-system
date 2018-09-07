package com.store.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store.domain.Supplier;
import com.store.factory.ConnectionFactory;

public class MySqlSupplierDAO implements SupplierDAO {


	public void deleteSupplier(Supplier supplier) throws RuntimeException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM suppliers ");
		sql.append("WHERE supplier_id = ? ");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, supplier.getSupplierId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void updateSupplier(Supplier supplier) throws RuntimeException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE suppliers ");
		sql.append("SET description = ? ");
		sql.append("WHERE supplier_id = ? ");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, supplier.getDescription());
			ps.setInt(2, supplier.getSupplierId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public Supplier selectSupplierById(int id) throws RuntimeException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT supplier_id, description");
		sql.append("FROM suppliers ");
		sql.append("WHERE supplier_id = ? ");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, id);
			//ps.executeUpdate();
			
			ResultSet result = ps.executeQuery();
			Supplier p = null;
			
			if(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				p = new Supplier();
				p.setSupplierId(result.getInt("supplier_id"));
				p.setDescription(result.getString("description"));
			}
			return p;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public List<Supplier> selectSuppliersByDescription(String description) throws RuntimeException {
		
		List<Supplier> suppliers = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT supplier_id, description ");
		sql.append("FROM suppliers ");
		sql.append("WHERE description LIKE ? ");
		sql.append("ORDER BY supplier_id ASC");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + description + "%");
			
			ResultSet result = ps.executeQuery();
			
			suppliers = new ArrayList<Supplier>();
			
			while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				Supplier p = new Supplier();
				p.setSupplierId(result.getInt("supplier_id"));
				p.setDescription(result.getString("description"));
				suppliers.add(p);
			}
			
			return suppliers;
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public List<Supplier> listSuppliers() throws RuntimeException {
		
		List<Supplier> suppliers = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM suppliers ");
		sql.append("ORDER BY supplier_id ASC");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
		
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet result = ps.executeQuery();
			suppliers = new ArrayList<Supplier>();
			
			while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				Supplier p = new Supplier();
				p.setSupplierId(result.getInt("supplier_id"));
				p.setDescription(result.getString("description"));
				suppliers.add(p);
			}
			
			return suppliers;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void insertSupplier(Supplier supplier) throws RuntimeException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO suppliers ");
		sql.append("(description) ");
		sql.append("VALUES (?)");
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, supplier.getDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
}
