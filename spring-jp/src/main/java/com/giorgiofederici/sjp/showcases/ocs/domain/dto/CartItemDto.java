package com.giorgiofederici.sjp.showcases.ocs.domain.dto;

import java.io.Serializable;

public class CartItemDto implements Serializable {

	private static final long serialVersionUID = 1473810469772547143L;

	private Integer cartItemId;
	private Integer productId;
	private int quantity;

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
