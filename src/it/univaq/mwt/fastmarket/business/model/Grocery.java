package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"intoleranceCategory"})
public class Grocery extends Product implements Serializable {

	private Date expirationDate;
	
	private float pricePerKg;
	private float pricePerLt;
	private IntoleranceCategory intoleranceCategory;
	
	public Grocery() {
		super();
	}

	public Grocery(long id, String name, String description, float price, int stock, Brand brand, Category category, String path, Date expirationDate, float pricePerKg, float pricePerLt, IntoleranceCategory intoleranceCategory, Image image) {
		super(id, name, description, price, stock, brand, category, path);
		this.expirationDate = expirationDate;
		this.pricePerKg = pricePerKg;
		this.pricePerLt = pricePerLt;
		this.intoleranceCategory = intoleranceCategory;		
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public float getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(float pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public float getPricePerLt() {
		return pricePerLt;
	}

	public void setPricePerLt(float pricePerLt) {
		this.pricePerLt = pricePerLt;
	}

	public IntoleranceCategory getIntoleranceCategory() {
		return intoleranceCategory;
	}

	public void setIntoleranceCategory(IntoleranceCategory intoleranceCategory) {
		this.intoleranceCategory = intoleranceCategory;
	}
	
}
