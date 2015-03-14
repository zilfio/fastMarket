package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"brand","category"})
public class Product implements Serializable {
	private long id;
	private String name;
	private String description;
	private float price;
	private int stock;
	private Brand brand;
	private Category category;
	
	private String path;
	
	public Product() {
		super();
	}

	public Product(long id, String name, String description, float price,
			int stock, Brand brand, Category category, String path) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.brand = brand;
		this.category = category;
		this.path = path;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}