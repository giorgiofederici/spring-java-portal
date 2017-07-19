package com.giorgiofederici.sjp.showcases.ocs.service;

import com.giorgiofederici.sjp.showcases.ocs.domain.dto.CartDto;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart;

public interface OcsCartService {

	void create(CartDto cartDto);

	OcsCart read(String cartId);

	void update(String cartId, CartDto cartDto);

	void delete(String cartId);

	void addItem(String cartId, Integer productId);

	void removeItem(String cartId, Integer productId);

	public OcsCart validate(String cartId);

	public void clearCart(String cartId);

	public int getTotalCartItemsNumber(String cartId);

}
