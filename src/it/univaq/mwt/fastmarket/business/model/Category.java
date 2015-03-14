package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Set;

public class Category implements Serializable {
	private long id;
	private String name;
	private String description;

	public Category() {
		super();
	}

	public Category(long id, String name, String description,
			Set<Product> products) {
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
