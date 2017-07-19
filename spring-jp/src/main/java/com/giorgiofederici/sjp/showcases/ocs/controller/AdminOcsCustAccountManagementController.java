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
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CustomerAccountForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCustomerAccountService;

@Controller
@RequestMapping(value = "admin/ocs/customer-accounts-management")
public class AdminOcsCustAccountManagementController {

	private final static Logger LOGGER = Logger.getLogger(AdminOcsCustAccountManagementController.class);

	@Autowired
	private OcsCustomerAccountService customerAccountService;

	@GetMapping
	public String getCategoriesManagement() {
		return "admin/ocs/customer-accounts-management";
	}

	@GetMapping(value = "/customer-accounts", params = { "draw", "start", "length", "order[0][column]", "order[0][dir]",
			"search[value]" })
	@ResponseBody
	public AjaxDataTableResponse getCustomerAccountsManagementPaginated(@RequestParam("draw") int draw,
			@RequestParam("start") int start, @RequestParam("length") int length,
			@RequestParam("order[0][column]") int orderColumn, @RequestParam("order[0][dir]") String orderDir,
			@RequestParam("search[value]") String searchValue, HttpServletResponse response) {

		AjaxDataTableResponse dataTableResponse = new AjaxDataTableResponse();
		dataTableResponse.setDraw(draw);

		int totalCustomerAccountsNumber = this.customerAccountService.getTotalCustomerAccountsNumber();

		dataTableResponse.setRecordsTotal(totalCustomerAccountsNumber);
		dataTableResponse.setRecordsFiltered(totalCustomerAccountsNumber);

		List<OcsCustomerAccount> customerAccounts = this.customerAccountService.findAll(start, length, orderColumn,
				orderDir, searchValue);

		dataTableResponse.setData(customerAccounts);

		return dataTableResponse;

	}

	@GetMapping(value = "/edit/{username}")
	public String getCategory(Model model, @PathVariable("username") String username) {

		OcsCustomerAccount customerAccount = this.customerAccountService.getCustomerAccountByUsername(username);
		CustomerAccountForm customerAccountForm = new CustomerAccountForm();
		customerAccountForm.setUsername(customerAccount.getUsername());
		customerAccountForm.setFirstName(customerAccount.getFirstName());
		customerAccountForm.setLastName(customerAccount.getLastName());
		customerAccountForm.setMidName(customerAccount.getMidName());
		customerAccountForm.setEmail(customerAccount.getEmail());
		customerAccountForm.setMobile(customerAccount.getMobile());
		customerAccountForm.setBirthDate(customerAccount.getBirthDate());
		customerAccountForm.setAddress(customerAccount.getAddress());

		model.addAttribute("customerAccountForm", customerAccountForm);

		return "admin/ocs/customer-accounts-management/edit";

	}

	@PostMapping(value = "/edit/{username}")
	public String submitForm(Model model,
			@ModelAttribute("customerAccountForm") @Valid CustomerAccountForm customerAccountForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("customerAccountForm", customerAccountForm);
			return "admin/ocs/customer-accounts-management/edit";
		} else {
			this.customerAccountService.updateCustomerAccount(customerAccountForm);
			return "redirect:/admin/ocs/customer-accounts-management";
		}
	}

	@PostMapping(value = "/delete/{username}")
	public String deleteCategory(Model model, @PathVariable("username") String username) {

		this.customerAccountService.deleteCustomerAccount(username);

		return "redirect:/admin/ocs/customer-accounts-management";

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

}
