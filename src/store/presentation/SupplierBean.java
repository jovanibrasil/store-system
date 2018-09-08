package store.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import store.business.domain.Supplier;
import store.persistence.MySqlSupplierDAO;

@ManagedBean(name = "beanSupplier")
@ViewScoped
public class SupplierBean {

	private ArrayList<Supplier> suppliers;
	private Supplier supplier;
	private ArrayList<Supplier> filteredSuppliers;

	public ArrayList<Supplier> getFilteredSuppliers() {
		return filteredSuppliers;
	}

	public void setFilteredSuppliers(ArrayList<Supplier> filteredSuppliers) {
		this.filteredSuppliers = filteredSuppliers;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> supplier) {
		this.suppliers = supplier;
	}
	
	@PostConstruct // executa antes da página ser visualizada
	public void populateSuppliersList() {
		MySqlSupplierDAO pDAO = new MySqlSupplierDAO();
		this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
	}
	
	public void initSupplier(){
		this.supplier = new Supplier();
	}
	
	public void insertSupplier(){
		
		try {
			MySqlSupplierDAO pDAO = new MySqlSupplierDAO();
			pDAO.insertSupplier(supplier);
			this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
			JSFUtil.createSuccessMessage("Success!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createErrorMessage("Error!");
		}
		
	}
	
	public void removeSupplier(){
		
		try {
			MySqlSupplierDAO pDAO = new MySqlSupplierDAO();
			pDAO.deleteSupplier(supplier);
			this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());			
			JSFUtil.createSuccessMessage("Success!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createErrorMessage("Error!");
		}

	}
	
	public void updateSupplier(){
	
		try {
			MySqlSupplierDAO pDAO = new MySqlSupplierDAO();
			pDAO.updateSupplier(supplier);
			this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
			JSFUtil.createSuccessMessage("Success!");
		} catch (Exception e) {
			JSFUtil.createErrorMessage("Error!");
		}
		
	}
	
}
