package store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import store.business.domain.Product;
import store.business.domain.Supplier;
import store.persistence.connections.ConnectionFactory;

public class MySqlProductDAO implements ProductDAO {

	private SessionFactory factory;
	
	public MySqlProductDAO() {
		this.factory = ConnectionFactory.getSessionFactory();
	}
		
	public void insertProduct(Product product) throws RuntimeException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
		
		} catch (Exception e) {
			if(tx != null) // se fez algo e ocorreu uma exception, desfaz o que foi feito
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
	
	public void deleteProduct(Product product) throws RuntimeException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
            Product p = (Product)session.load(Product.class, product.getProductId());
            session.delete(p);
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

	public void updateProduct(Product product) throws RuntimeException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
            session.update(product); 
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

	public Product selectProductById(int id) throws RuntimeException {

		Product product = null;
		Session session = factory.openSession();
		
		try {
			String hql = "from product as prod "
					+ "inner join prod.supplyer as sup "
					+ "where prod.product_id="+id;
			List<?> list = session.createQuery(hql).list();
			product = new Product();
			Object[] row = (Object[]) list.get(0);	
			Product p = (Product)row[0];
			p.setSupplier((Supplier)row[1]);
			
			return product;
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

	public List<Product> listProducts() throws RuntimeException {

		List<Product> products = null;
		Session session = factory.openSession();
		
		try {
			String hql = "from Product as prod inner join prod.supplier as sup";
			List<?> list = session.createQuery(hql).list();
			products = new ArrayList<Product>();
			for (Object object : list) {
				Object[] row = (Object[]) object;
				Product p = (Product)row[0];
				p.setSupplier((Supplier)row[1]);
				products.add(p);				
			}
			return products;
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
