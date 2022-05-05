package com.training.model;

import com.training.entity.ProductEntity;

public class CartModel {
	private ProductEntity productEntity;
	private int quantity;
	
	public CartModel(ProductEntity productEntity, int quantity) {
		super();
		this.productEntity = productEntity;
		this.quantity = quantity;
	}
	public ProductEntity getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	public int getQuality() {
		return quantity;
	}
	public void setQuality(int quantity) {
		this.quantity = quantity;
	}
	
}
