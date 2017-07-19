package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ocs_cart_item", catalog = "sjp")
public class OcsCartItem implements Serializable {

	private static final long serialVersionUID = 623438703118832840L;

	private CartItemPrimaryKey cartItemPrimaryKey;
	private OcsProduct product;
	// private OcsCart cart;
	private int quantity;
	private BigDecimal totalPrice;

	public OcsCartItem() {

	}

	@EmbeddedId
	public CartItemPrimaryKey getCartItemPrimaryKey() {
		return cartItemPrimaryKey;
	}

	public void setCartItemPrimaryKey(CartItemPrimaryKey cartItemPrimaryKey) {
		this.cartItemPrimaryKey = cartItemPrimaryKey;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	public OcsProduct getProduct() {
		return product;
	}

	public void setProduct(OcsProduct product) {
		this.product = product;
		this.updateTotalPrice();
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Transient
	public BigDecimal getTotalPrice() {
		this.updateTotalPrice();
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void updateTotalPrice() {
		totalPrice = BigDecimal.valueOf(this.product.getPrice()).multiply(new BigDecimal(this.quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartItemPrimaryKey == null) ? 0 : cartItemPrimaryKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OcsCartItem other = (OcsCartItem) obj;
		if (cartItemPrimaryKey == null) {
			if (other.cartItemPrimaryKey != null)
				return false;
		} else if (!cartItemPrimaryKey.equals(other.cartItemPrimaryKey))
			return false;
		return true;
	}

}
