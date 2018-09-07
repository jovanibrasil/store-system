package store.persistence;

import java.util.List;

import store.business.domain.Supplier;

public interface SupplierDAO {

	public void deleteSupplier(Supplier supplier);		
	public void updateSupplier(Supplier supplier);
	public Supplier selectSupplierById(int id);
	public List<Supplier> selectSuppliersByDescription(String description);
	public List<Supplier> listSuppliers();
	public void insertSupplier(Supplier supplier); 
	
}
