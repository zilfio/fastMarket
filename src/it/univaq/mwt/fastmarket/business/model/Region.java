package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class Region implements Serializable {
	private long id;
	private String name;
	
	public Region() {
		super();
	}

	public Region(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
