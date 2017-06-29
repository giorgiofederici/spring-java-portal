package com.giorgiofederici.sjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.giorgiofederici.sjp.domain.form.SignInForm;
import com.giorgiofederici.sjp.service.UserService;

@Controller
@RequestMapping(value = "/signin")
public class SignInController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {

		SignInForm signInForm = new SignInForm();
		model.addAttribute("signInForm", signInForm);

		return "signin";
	}

	// @RequestMapping(method = RequestMethod.POST)
	// public String submitForm(Model model, @ModelAttribute("signInForm")
	// @Valid SignInForm signInForm,
	// BindingResult bindingResult) {
	//
	// if (bindingResult.hasErrors()) {
	// return "/signin?error";
	// } else {
	// return "index";
	// }
	//
	// }
	//
	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// // add validators here
	// }

}
