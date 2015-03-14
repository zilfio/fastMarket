package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"province"})
public class District implements Serializable {
	private long id;
	private String name;
	private Province province;
	private boolean enableService;

	public District() {
		super();
	}
	
	public District(long id, String name, Province province, boolean enableService) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
		this.enableService = enableService;
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
	public boolean isEnableService() {
		return enableService;
	}

	public void setEnableService(boolean enableService) {
		this.enableService = enableService;
	}
}
