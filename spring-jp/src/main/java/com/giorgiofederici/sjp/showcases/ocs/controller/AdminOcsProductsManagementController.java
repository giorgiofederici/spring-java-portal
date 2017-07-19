package com.giorgiofederici.sjp.showcases.ocs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.giorgiofederici.sjp.domain.response.AjaxDataTableResponse;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.ProductForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCategoryService;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsProductService;

@Controller
@RequestMapping(value = "admin/ocs/products-management")
public class AdminOcsProductsManagementController {

	private final static Logger LOGGER = Logger.getLogger(AdminOcsProductsManagementController.class);

	@Autowired
	private OcsProductService productService;

	@Autowired
	private OcsCategoryService categoryService;

	@GetMapping
	public String getProductsManagement(Model model) {
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);

		List<OcsCategory> categories = this.categoryService.findAll();
		model.addAttribute("categories", categories);

		return "admin/ocs/products-management";
	}

	@PostMapping
	public String submitProductForm(Model model, @ModelAttribute("productForm") @Valid ProductForm productForm,
			BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			this.productService.createProduct(productForm);
			productForm = new ProductForm();
			model.addAttribute("productForm", productForm);
		}

		return "redirect:/admin/ocs/products-management";

	}

	@GetMapping(value = "/products", params = { "draw", "start", "length", "order[0][column]", "order[0][dir]",
			"search[value]" })
	@ResponseBody
	public AjaxDataTableResponse getProductsManagementPaginated(@RequestParam("draw") int draw,
			@RequestParam("start") int start, @RequestParam("length") int length,
			@RequestParam("order[0][column]") int orderColumn, @RequestParam("order[0][dir]") String orderDir,
			@RequestParam("search[value]") String searchValue, HttpServletResponse response) {

		AjaxDataTableResponse dataTableResponse = new AjaxDataTableResponse();
		dataTableResponse.setDraw(draw);

		int totalProductsNumber = this.productService.getTotalProductsNumber();

		dataTableResponse.setRecordsTotal(totalProductsNumber);
		dataTableResponse.setRecordsFiltered(totalProductsNumber);

		List<ProductForm> products = this.productService.findAll(start, length, orderColumn, orderDir, searchValue);

		dataTableResponse.setData(products);

		return dataTableResponse;

	}

	@GetMapping(value = "/edit/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {

		OcsProduct product = this.productService.getProductById(id);
		ProductForm productForm = new ProductForm();
		productForm.setId(product.getId());
		productForm.setName(product.getName());

		model.addAttribute("productForm", productForm);

		return "admin/ocs/products-management/edit";

	}

	@PostMapping(value = "/edit/{id}")
	public String submitForm(Model model, @ModelAttribute("productsForm") @Valid ProductForm productForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("productForm", productForm);
			return "admin/ocs/products-management/edit";
		} else {
			this.productService.updateProduct(productForm);
			return "redirect:/admin/ocs/products-management";
		}
	}

	@PostMapping(value = "/delete/{id}")
	public String deleteProduct(Model model, @PathVariable("id") Integer id) {

		this.productService.deleteProduct(id);

		return "redirect:/admin/ocs/products-management";

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

}
