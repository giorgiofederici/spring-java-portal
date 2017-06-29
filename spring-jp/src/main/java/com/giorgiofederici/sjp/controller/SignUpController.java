package com.giorgiofederici.sjp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.giorgiofederici.sjp.domain.form.SignUpForm;
import com.giorgiofederici.sjp.service.UserService;

@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {
		SignUpForm signUpForm = new SignUpForm();
		model.addAttribute("signUpForm", signUpForm);
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("signUpForm") @Valid SignUpForm signUpForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("signUpForm", signUpForm);
			return "signup";
		} else {

			this.userService.createUser(signUpForm);
			return "redirect:/signup/success";
		}
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String getSignUpSuccess(Model model) {
		return "signup-success";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("username", "email", "password", "matchingPassword");
		// binder.setValidator(productValidator);
	}

}
