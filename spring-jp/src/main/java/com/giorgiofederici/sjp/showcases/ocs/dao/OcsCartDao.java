package com.giorgiofederici.sjp.showcases.ocs.dao;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCartItem;

public interface OcsCartDao {

	public OcsCart createCart(OcsCart cart);

	public OcsCart getCartById(String cartId);

	public List<OcsCart> findAll();

	public List<OcsCart> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalCartItemsNumber(String cartId);

	public void updateCart(OcsCart cart);

	public void deleteCart(String cartId);

	public void clearCart(String cartId);

	public void createCartItem(OcsCartItem cartItem);

	public void updateCartItem(OcsCartItem cartItem);

	public void removeItem(String cartId, Integer productId);

	public void updateCartItemQuantity(OcsCartItem cartItem);

}
