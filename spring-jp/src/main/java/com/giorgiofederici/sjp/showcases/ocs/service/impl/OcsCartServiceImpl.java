package com.giorgiofederici.sjp.showcases.ocs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCartDao;
import com.giorgiofederici.sjp.showcases.ocs.dao.OcsProductDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.dto.CartDto;
import com.giorgiofederici.sjp.showcases.ocs.domain.dto.CartItemDto;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.CartItemPrimaryKey;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCartItem;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.exception.InvalidCartException;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCartService;

@Service
@Transactional
public class OcsCartServiceImpl implements OcsCartService {

	@Autowired
	private OcsCartDao cartDao;

	@Autowired
	private OcsProductDao productDao;

	@Override
	public void create(CartDto cartDto) {

		OcsCart cart = new OcsCart();

		List<OcsCartItem> cartItems = new ArrayList<OcsCartItem>();

		for (CartItemDto cartItemDto : cartDto.getCartItems()) {
			OcsProduct product = this.productDao.getProductById(cartItemDto.getProductId());
			OcsCartItem cartItem = new OcsCartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(cartItemDto.getQuantity());
			cartItems.add(cartItem);

		}

		cart.setCartItems(cartItems);
		cart = this.cartDao.createCart(cart);

	}

	@Override
	public OcsCart read(String cartId) {
		return this.cartDao.getCartById(cartId);
	}

	@Override
	public void update(String cartId, CartDto cartDto) {

		OcsCart cart = this.cartDao.getCartById(cartId);
		List<CartItemDto> cartItems = cartDto.getCartItems();

		for (CartItemDto cartItemDto : cartItems) {

			OcsCartItem cartItem = new OcsCartItem();
			CartItemPrimaryKey cartItemPrimaryKey = new CartItemPrimaryKey(cartItemDto.getCartItemId(), cart);
			cartItem.setCartItemPrimaryKey(cartItemPrimaryKey);
			OcsProduct product = this.productDao.getProductById(cartItemDto.getProductId());
			cartItem.setProduct(product);
			cartItem.setQuantity(cartItemDto.getQuantity());

			this.cartDao.updateCartItem(cartItem);
		}

	}

	@Override
	public void delete(String cartId) {
		this.cartDao.deleteCart(cartId);
	}

	@Override
	public void addItem(String cartId, Integer productId) {

		OcsCart cart = this.cartDao.getCartById(cartId);

		if (cart == null) {

			OcsCart newCart = new OcsCart();
			newCart.setId(cartId);
			this.cartDao.createCart(newCart);

			OcsCartItem cartItem = new OcsCartItem();
			CartItemPrimaryKey cartItemPrimaryKey = new CartItemPrimaryKey(newCart);
			cartItem.setCartItemPrimaryKey(cartItemPrimaryKey);
			OcsProduct product = this.productDao.getProductById(productId);
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			this.cartDao.createCartItem(cartItem);

		} else {
			if (cart.getItemByProductId(productId) == null) {
				OcsCartItem cartItem = new OcsCartItem();
				cartItem.setCartItemPrimaryKey(new CartItemPrimaryKey(cart));
				OcsProduct product = this.productDao.getProductById(productId);
				cartItem.setProduct(product);
				cartItem.setQuantity(1);
				this.cartDao.createCartItem(cartItem);
			} else {
				OcsCartItem existingCartItem = cart.getItemByProductId(productId);
				existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
				this.cartDao.updateCartItemQuantity(existingCartItem);
			}
		}

	}

	@Override
	public void removeItem(String cartId, Integer productId) {
		this.cartDao.removeItem(cartId, productId);
	}

	@Override
	public OcsCart validate(String cartId) {
		OcsCart cart = this.cartDao.getCartById(cartId);
		if (cart == null || cart.getCartItems().isEmpty()) {
			throw new InvalidCartException(cartId);
		}

		return cart;
	}

	@Override
	public void clearCart(String cartId) {
		this.cartDao.clearCart(cartId);
	}

	@Override
	public int getTotalCartItemsNumber(String cartId) {
		return this.cartDao.getTotalCartItemsNumber(cartId);
	}

}
