package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class CartItemPrimaryKey implements Serializable {

	private static final long serialVersionUID = -382648176057385121L;

	private Integer cartItemId;

	// private String cartId;
	private OcsCart cart;

	public CartItemPrimaryKey() {

	}

	public CartItemPrimaryKey(OcsCart cart) {
		this.cart = cart;
	}

	public CartItemPrimaryKey(Integer cartItemId, OcsCart cart) {
		this.cartItemId = cartItemId;
		this.cart = cart;
	}

	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	public OcsCart getCart() {
		return cart;
	}

	public void setCart(OcsCart cart) {
		this.cart = cart;
	}

}
