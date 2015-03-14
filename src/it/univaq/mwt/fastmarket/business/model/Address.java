package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"district","user"})
public class Address implements Serializable {
	private long id;
	private String street;
	private int streetNumber;
	private String country;
	private District district;
	private int zipCode;
	private User user;

	public Address() {
		super();
	}

	public Address(User user) {
		super();
		this.user = user;
	}

	public Address(long id, String street, int streetNumber, String country,
			District district, int zipCode, User user) {
		super();
		this.id = id;
		this.street = street;
		this.streetNumber = streetNumber;
		this.country = country;
		this.district = district;
		this.zipCode = zipCode;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
