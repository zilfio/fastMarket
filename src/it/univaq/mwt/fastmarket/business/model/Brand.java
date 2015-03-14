package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class Brand implements Serializable {
	private long id;
	private String name;
	private String description;
	private String provenance;
	
	public Brand() {
		super();
	}

	public Brand(long id, String name, String description, String provenance) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.provenance = provenance;
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

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}
	
}
