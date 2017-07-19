package com.giorgiofederici.sjp.showcases.ocs.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDto implements Serializable {

	private static final long serialVersionUID = 8820837491313671305L;

	private String cartId;
	private List<CartItemDto> cartItems;

	public CartDto() {
		this.cartItems = new ArrayList<CartItemDto>();
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

	public void addCartItem(CartItemDto cartItemDto) {
		this.cartItems.add(cartItemDto);
	}

}
