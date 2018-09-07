package com.store.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import com.store.DAO.SupplierDAO;
import com.store.domain.Supplier;
import com.store.util.JSFUtil;

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
		SupplierDAO pDAO = new SupplierDAO();
		this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
	}
	
	public void initSupplier(){
		this.supplier = new Supplier();
	}
	
	public void insertSupplier(){
		
		SupplierDAO pDAO = new SupplierDAO();
		if(pDAO.insertSupplier(supplier))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
		
	}
	
	public void removeSupplier(){
		
		SupplierDAO pDAO = new SupplierDAO();
		if(pDAO.deleteSupplier(supplier))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
		
	}
	
	public void updateSupplier(){
		
		SupplierDAO pDAO = new SupplierDAO();
		if(pDAO.updateSupplier(supplier))
			JSFUtil.createSuccessMessage("Success!");
		else
			JSFUtil.createErrorMessage("Error!");
		this.suppliers = new ArrayList<Supplier>(pDAO.listSuppliers());
	
		
	}
	
}
