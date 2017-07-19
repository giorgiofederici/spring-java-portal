package com.giorgiofederici.sjp.showcases.ocs.controller.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giorgiofederici.sjp.showcases.ocs.domain.dto.CartDto;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCartService;

@RestController
@RequestMapping(value = "showcases/ocs/rest/cart")
public class OcsCartRestController {

	@Autowired
	private OcsCartService cartService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody CartDto cartDto) {
		cartService.create(cartDto);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public OcsCart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	@RequestMapping(value = "/update/{cartId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
		cartDto.setCartId(cartId);
		cartService.update(cartId, cartDto);
	}

	@RequestMapping(value = "/delete/{cartId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}

	@RequestMapping(value = "/add/product/{productId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addItem(@PathVariable Integer productId, HttpSession session) {
		cartService.addItem(session.getId(), productId);
	}

	@RequestMapping(value = "/remove/product/{productId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void removeItem(@PathVariable Integer productId, HttpSession session) {
		cartService.removeItem(session.getId(), productId);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Integer countCartItems(HttpSession session) {
		return cartService.getTotalCartItemsNumber(session.getId());
	}

}
