package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"region"})
public class Province implements Serializable {
	private long id;
	private String name;
	private String abbreviation;
	private Region region;

	public Province() {
		super();
	}

	public Province(long id, String name, String abbreviation, Region region) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.region = region;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
