package com.store.DAO;

import java.sql.Connection;

import com.store.domain.Supplier;
import com.store.factory.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

	private Connection connection = null;
	
	public SupplierDAO() {
		try {
			this.connection = ConnectionFactory.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteSupplier(Supplier supplier) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM suppliers ");
		sql.append("WHERE supplier_id = ? ");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, supplier.getSupplierId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updateSupplier(Supplier supplier) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE suppliers ");
		sql.append("SET description = ? ");
		sql.append("WHERE supplier_id = ? ");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, supplier.getDescription());
			ps.setInt(2, supplier.getSupplierId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Supplier selectSupplierById(int id) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT supplier_id, description");
		sql.append("FROM suppliers ");
		sql.append("WHERE supplier_id = ? ");
		
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
				return p;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Supplier> selectSuppliersByDescription(String description) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT supplier_id, description ");
		sql.append("FROM suppliers ");
		sql.append("WHERE description LIKE ? ");
		sql.append("ORDER BY supplier_id ASC");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + description + "%");
			//ps.executeUpdate();
			
			ResultSet result = ps.executeQuery();
			
			List<Supplier> suppliers = new ArrayList<Supplier>();
			
			while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				Supplier p = new Supplier();
				p.setSupplierId(result.getInt("supplier_id"));
				p.setDescription(result.getString("description"));
				suppliers.add(p);
			}
			
			return suppliers;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Supplier> listSuppliers() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM suppliers ");
		sql.append("ORDER BY supplier_id ASC");
		
		try {
		
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet result = ps.executeQuery();
			List<Supplier> suppliers = new ArrayList<Supplier>();
			
			while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				Supplier p = new Supplier();
				p.setSupplierId(result.getInt("supplier_id"));
				p.setDescription(result.getString("description"));
				suppliers.add(p);
			}
			
			return suppliers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean insertSupplier(Supplier supplier) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO suppliers ");
		sql.append("(description) ");
		sql.append("VALUES (?)");
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, supplier.getDescription());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
