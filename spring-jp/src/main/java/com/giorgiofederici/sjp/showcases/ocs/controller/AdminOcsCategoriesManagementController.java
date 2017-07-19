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
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CategoryForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCategoryService;

@Controller
@RequestMapping(value = "admin/ocs/categories-management")
public class AdminOcsCategoriesManagementController {

	private final static Logger LOGGER = Logger.getLogger(AdminOcsCategoriesManagementController.class);

	@Autowired
	private OcsCategoryService categoryService;

	@GetMapping
	public String getCategoriesManagement(Model model) {
		CategoryForm categoryForm = new CategoryForm();
		model.addAttribute("categoryForm", categoryForm);
		return "admin/ocs/categories-management";
	}

	@PostMapping
	public String submitCategoryForm(Model model, @ModelAttribute("categoryForm") @Valid CategoryForm categoryForm,
			BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			this.categoryService.createCategory(categoryForm);
			categoryForm = new CategoryForm();
			model.addAttribute("categoryForm", categoryForm);
		}

		return "admin/ocs/categories-management";

	}

	@GetMapping(value = "/categories", params = { "draw", "start", "length", "order[0][column]", "order[0][dir]",
			"search[value]" })
	@ResponseBody
	public AjaxDataTableResponse getCategoriesManagementPaginated(@RequestParam("draw") int draw,
			@RequestParam("start") int start, @RequestParam("length") int length,
			@RequestParam("order[0][column]") int orderColumn, @RequestParam("order[0][dir]") String orderDir,
			@RequestParam("search[value]") String searchValue, HttpServletResponse response) {

		AjaxDataTableResponse dataTableResponse = new AjaxDataTableResponse();
		dataTableResponse.setDraw(draw);

		int totalCategoriesNumber = this.categoryService.getTotalCategoriesNumber();

		dataTableResponse.setRecordsTotal(totalCategoriesNumber);
		dataTableResponse.setRecordsFiltered(totalCategoriesNumber);

		List<OcsCategory> categories = this.categoryService.findAll(start, length, orderColumn, orderDir, searchValue);

		dataTableResponse.setData(categories);

		return dataTableResponse;

	}

	@GetMapping(value = "/edit/{id}")
	public String getCategory(Model model, @PathVariable("id") Integer id) {

		OcsCategory category = this.categoryService.getCategoryById(id);
		CategoryForm categoryForm = new CategoryForm();
		categoryForm.setId(category.getId());
		categoryForm.setName(category.getName());

		model.addAttribute("categoryForm", categoryForm);

		return "admin/ocs/categories-management/edit";

	}

	@PostMapping(value = "/edit/{id}")
	public String submitForm(Model model, @ModelAttribute("categoryForm") @Valid CategoryForm categoryForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("categoryForm", categoryForm);
			return "admin/ocs/categories-management/edit";
		} else {
			this.categoryService.updateCategory(categoryForm);
			return "redirect:/admin/ocs/categories-management";
		}
	}

	@PostMapping(value = "/delete/{id}")
	public String deleteCategory(Model model, @PathVariable("id") Integer id) {

		this.categoryService.deleteCategory(id);

		return "redirect:/admin/ocs/categories-management";

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "name");
	}

}
