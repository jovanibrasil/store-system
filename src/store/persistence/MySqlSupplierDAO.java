package store.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import store.business.domain.Product;
import store.business.domain.Supplier;
import store.persistence.connections.ConnectionFactory;

public class MySqlSupplierDAO implements SupplierDAO {

	private SessionFactory sessionfactory;
	
	public MySqlSupplierDAO() {
		this.sessionfactory = ConnectionFactory.getSessionFactory(); 
	}

	public void deleteSupplier(Supplier supplier) throws RuntimeException {
		
		Session session = sessionfactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Supplier s = (Supplier)session.load(Supplier.class, supplier.getSupplierId());
            session.delete(s);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) 
                tx.rollback();
            e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void updateSupplier(Supplier supplier) throws RuntimeException {
		
		Session session = sessionfactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(supplier);
			tx.commit();
		} catch (Exception e) {
			if(tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public Supplier selectSupplierById(int id) throws RuntimeException {
		
		Session session = this.sessionfactory.openSession();
		Transaction tx = null;
		try {
			
			tx = session.beginTransaction();
			
			String hql = "from Supplier as sup "
					+ "where sup.supplier_id="+id;
			List<?> list = session.createQuery(hql).list();
			
			Supplier p = new Supplier();
			Object[] row = (Object[]) list.get(0);	
			Supplier s = (Supplier)row[0];
			tx.commit();
			
			return p;
		} catch (Exception e) {
			if(tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public List<Supplier> selectSuppliersByDescription(String description) throws RuntimeException {
		
		List<Supplier> suppliers = null;

		Product product = null;
		Session session = this.sessionfactory.openSession();
		Transaction tx = null;
		try {
			
			//tx = session.beginTransaction();
			
//			String hql = "from supplier as sup "
//					+ "where sup.description="+id;
//			List<?> list = session.createQuery(hql).list();
//			
//			
			Supplier s = new Supplier();
			s.setDescription(description);
			Example example = Example.create(s);
			example.enableLike(MatchMode.ANYWHERE);
			
			Criteria criteria = session.createCriteria(Supplier.class, "description");
			criteria.add(example);  
			suppliers = criteria.list();
			
//			products = new ArrayList<Product>();
//			for (Object object : list) {
//				Object[] row = (Object[]) object;
//				Product p = (Product)row[0];
//				p.setSupplier((Supplier)row[1]);
//				products.add(p);				
//			}
//			Supplier p = new Supplier();
//			Object[] row = (Object[]) list.get(0);	
//			Supplier s = (Supplier)row[0];
//			tx.commit();
//			
			
			
//			PreparedStatement ps = connection.prepareStatement(sql.toString());
//			ps.setString(1, "%" + description + "%");
//			
//			ResultSet result = ps.executeQuery();
//			
			//suppliers = new ArrayList<Supplier>();
			
	//		while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
				//Supplier p = new Supplier();
//				p.setSupplierId(result.getInt("supplier_id"));
//				p.setDescription(result.getString("description"));
//				suppliers.add(p);
//			}
//			
			return suppliers;
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public List<Supplier> listSuppliers() throws RuntimeException {
		
		List<Supplier> suppliers = null;
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM suppliers ");
//		sql.append("ORDER BY supplier_id ASC");		
//		Connection connection = ConnectionFactory.getConnection();
		Session session = this.sessionfactory.openSession();
		
		try {
		
//			PreparedStatement ps = connection.prepareStatement(sql.toString());
//			ResultSet result = ps.executeQuery();
			//suppliers = new ArrayList<Supplier>();
			
//			while(result.next()) { // Por default o cursor está setado para uma posição antes da inicial.
//				Supplier p = new Supplier();
//				p.setSupplierId(result.getInt("supplier_id"));
//				p.setDescription(result.getString("description"));
//				suppliers.add(p);
//			}
			//tx = session.beginTransaction();
			
			String hql = "from Supplier as sup";
			//List<?> list
			suppliers = session.createQuery(hql).list();
			
//			for (Object object : list) {
//				
//				Supplier p = new Supplier();
//				Object[] row = (Object[]) list.get(0);	
//				Supplier s = (Supplier)row[0];
//				
//				
//			}
			//tx.commit();
			
			
			return suppliers;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void insertSupplier(Supplier supplier) throws RuntimeException {
				
		Session session = this.sessionfactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(supplier);
			tx.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
}
