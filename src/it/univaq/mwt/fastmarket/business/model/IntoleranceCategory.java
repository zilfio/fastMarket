package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class IntoleranceCategory implements Serializable {
	
	private long id;
	private String name;
	private String description;
	
	public IntoleranceCategory() {
		super();
	}
	
	public IntoleranceCategory(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
