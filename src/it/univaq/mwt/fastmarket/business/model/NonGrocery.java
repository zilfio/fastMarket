package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class NonGrocery extends Product implements Serializable {

	private String scopeOfUse;
	
	public NonGrocery() {
		super();
	}

	public NonGrocery(long id, String name, String description, float price, int stock, Brand brand, Category category, String path, String scopeOfUse) {
		super(id, name, description, price, stock, brand, category, path);
		this.scopeOfUse = scopeOfUse;			
	}

	public String getScopeOfUse() {
		return scopeOfUse;
	}

	public void setScopeOfUse(String scopeOfUse) {
		this.scopeOfUse = scopeOfUse;
	}
	
}
