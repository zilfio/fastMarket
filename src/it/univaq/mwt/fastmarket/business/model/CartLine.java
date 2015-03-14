package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class CartLine implements Serializable {
	private long id;
	private Product product;
	private int quantity;
	private Cart cart;
	
	public CartLine() {
		super();
	}
	
	public CartLine(long id, Product product, Cart cart, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.cart = cart;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
