package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ocs_cart", catalog = "sjp")
public class OcsCart implements Serializable {

	private static final long serialVersionUID = 2345361663463678069L;

	private String id;
	private List<OcsCartItem> cartItems;
	private BigDecimal grandTotal;

	public OcsCart() {

	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cartItemPrimaryKey.cart")
	public List<OcsCartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<OcsCartItem> cartItems) {
		this.cartItems = cartItems;
		// if (this.cartItems != null && !this.cartItems.isEmpty()) {
		// for (OcsCartItem cartItem : this.cartItems) {
		// CartItemPrimaryKey cartItemPrimaryKey = new CartItemPrimaryKey();
		// cartItemPrimaryKey.setCart(this);
		// cartItem.setCartItemPrimaryKey(cartItemPrimaryKey);
		// }
		// }
	}

	@Transient
	public BigDecimal getGrandTotal() {
		updateGrandTotal();
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void updateGrandTotal() {

		Function<OcsCartItem, BigDecimal> totalMapper = cartItem -> cartItem.getTotalPrice();

		BigDecimal grandTotal = cartItems.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

		this.setGrandTotal(grandTotal);
	}

	public OcsCartItem getItemByProductId(Integer productId) {
		return cartItems.stream().filter(cartItem -> cartItem.getProduct().getId().equals(productId)).findAny()
				.orElse(null);
	}

}
