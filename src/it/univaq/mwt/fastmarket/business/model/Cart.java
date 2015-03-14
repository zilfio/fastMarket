package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class Cart implements Serializable {
	private long id;
	private String name;
	private float totalPrice;
	private User user;
	
	public Cart() {
		super();
	}

	public Cart(long id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
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
	
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
