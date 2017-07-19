package com.giorgiofederici.sjp.showcases.ocs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CustomerAccountForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCustomerAccountService;

@Controller
@RequestMapping(value = "/user/ocs")
public class UserOcsProfileController {

	@Autowired
	private OcsCustomerAccountService customerAccountService;

	@GetMapping
	public String getOcsProfile(Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		OcsCustomerAccount customerAccount = this.customerAccountService.getCustomerAccountByUsername(username);
		model.addAttribute("customerAccount", customerAccount);
		model.addAttribute("username", username);

		return "user/ocs";
	}

	@GetMapping(value = "/customer-account", params = { "user" })
	public String initForm(Model model, @RequestParam("user") String user) {
		CustomerAccountForm customerAccountForm = new CustomerAccountForm();
		customerAccountForm.setUsername(user);
		model.addAttribute("customerAccountForm", customerAccountForm);
		return "user/ocs/customer-account";
	}

	@PostMapping(value = "/customer-account")
	public String submitForm(Model model,
			@ModelAttribute("customerAccountForm") CustomerAccountForm customerAccountForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "user/ocs/customer-account";
		} else {
			this.customerAccountService.createCustomerAccount(customerAccountForm);
			return "redirect:/user/ocs";
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
