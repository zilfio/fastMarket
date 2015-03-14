package it.univaq.mwt.fastmarket.business.model;

import java.io.Serializable;

public class Image implements Serializable {

	private byte[] image;
	
	public Image() {
		super();
	}

	public Image(byte[] image) {
		super();
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
