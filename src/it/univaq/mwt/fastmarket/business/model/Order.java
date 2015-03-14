package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Order implements Serializable {
	private long id;
	private Date creationDate;
	private float totalPrice;
	private Cart cart;
	
	public Order() {
		super();
	}

	public Order(long id, Date creationDate, float totalPrice, Cart cart) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.totalPrice = totalPrice;
		this.cart = cart;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
