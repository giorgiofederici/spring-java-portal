package com.giorgiofederici.sjp.showcases.ocs.webflow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsProductService;

@Controller
@RequestMapping("showcases/ocs/webflow")
public class OcsWebFlowController {

	@Autowired
	private OcsProductService productService;

	@RequestMapping(value = "/cart")
	public String get(HttpServletRequest request) {
		return "redirect:/showcases/ocs/webflow/cart/" + request.getSession(true).getId();
	}

	@RequestMapping(value = "/cart/{cartId}", method = RequestMethod.GET)
	public String getCart(@PathVariable(value = "cartId") String cartId, Model model) {
		model.addAttribute("cartId", cartId);
		return "showcases/ocs/webflow/cart";
	}

	@RequestMapping(value = "/shopping", method = RequestMethod.GET)
	public String getWebFlowShopping(Model model) {
		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);
		return "showcases/ocs/webflow/shopping";
	}

}
