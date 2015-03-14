package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;
import java.util.Date;

public class Delivery extends Order implements Serializable {
	private Date dispatchDate;
	
	private float deliveryCosts;
	private String deliveryType;
	
	public Delivery() {
		super();
	}
	
	public Delivery(long id, Date creationDate, float totalPrice, Cart cart) {
		super(id, creationDate, totalPrice, cart);
	}
	
	public Date getDispatchDate() {
		return dispatchDate;
	}
	
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
	public float getDeliveryCosts() {
		return deliveryCosts;
	}
	
	public void setDeliveryCosts(float deliveryCosts) {
		this.deliveryCosts = deliveryCosts;
	}
	
	public String getDeliveryType() {
		return deliveryType;
	}
	
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	
}
