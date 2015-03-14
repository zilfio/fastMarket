package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"user"})
public class SystemInformation implements Serializable {
	private long id;
	private String name;
	private String description;

	private Date creationDate;
	
	private User user;
	
	public SystemInformation() {
		super();
	}

	public SystemInformation(long id, String name, String description,
			Date creationDate, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.user = user;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
