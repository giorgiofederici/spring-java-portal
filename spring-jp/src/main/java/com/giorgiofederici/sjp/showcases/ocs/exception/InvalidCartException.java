package com.giorgiofederici.sjp.showcases.ocs.exception;

public class InvalidCartException extends RuntimeException {

	private static final long serialVersionUID = -8053382921843709664L;

	private String cartId;

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

}
