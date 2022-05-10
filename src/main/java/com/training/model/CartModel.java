package com.training.model;

import com.training.entity.ProductEntity;

public class CartModel {
	private ProductEntity productEntity;
	private int quantity;
	
	public CartModel(ProductEntity productEntity, int quantity) {
		super();
		this.productEntity = productEntity;
		this.setQuantity(quantity);
	}
	public ProductEntity getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
