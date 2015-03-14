package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Date;

public class Booking extends Order implements Serializable {
	private Date expirationDate;

	public Booking() {
		super();
	}

	public Booking(long id, Date creationDate, float totalPrice, Cart cart) {
		super(id, creationDate, totalPrice, cart);
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
