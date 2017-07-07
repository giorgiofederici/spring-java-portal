package com.giorgiofederici.sjp.controller.admin;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.form.UserForm;
import com.giorgiofederici.sjp.domain.response.AjaxDataTableResponse;
import com.giorgiofederici.sjp.service.UserService;

@Controller
@RequestMapping(value = "/admin/users-management")
public class AdminUsersManagementController {

	private final static Logger LOGGER = Logger.getLogger(AdminUsersManagementController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public String getUsersManagement(Model model) throws JsonProcessingException {
		return "admin/users-management";
	}

	@GetMapping(value = "/users", params = { "draw", "start", "length", "order[0][column]", "order[0][dir]",
			"search[value]" })
	@ResponseBody
	public AjaxDataTableResponse getUsersManagementPaginated(@RequestParam("draw") int draw,
			@RequestParam("start") int start, @RequestParam("length") int length,
			@RequestParam("order[0][column]") int orderColumn, @RequestParam("order[0][dir]") String orderDir,
			@RequestParam("search[value]") String searchValue, HttpServletResponse response) {

		AjaxDataTableResponse dataTableResponse = new AjaxDataTableResponse();
		dataTableResponse.setDraw(draw);

		dataTableResponse.setRecordsTotal(this.userService.getTotalUsersNumber());
		dataTableResponse.setRecordsFiltered(this.userService.getTotalUsersNumber());

		List<User> users = this.userService.findAll(start, length, orderColumn, orderDir, searchValue);

		dataTableResponse.setData(users);

		return dataTableResponse;

	}

	@GetMapping(value = "/edit/{username}")
	public String getUser(Model model, @PathVariable("username") String username) {

		User user = this.userService.getUserByUsername(username);
		UserForm userForm = new UserForm();
		userForm.setUsername(user.getUsername());
		userForm.setEmail(user.getEmail());
		userForm.setEnabled(user.isEnabled());

		model.addAttribute("userForm", userForm);

		return "admin/users-management/edit";

	}

	@PostMapping(value = "/edit/{username}")
	public String submitForm(Model model, @ModelAttribute("userForm") @Valid UserForm userForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "admin/users-management/edit";
		} else {
			this.userService.updateUser(userForm);
			return "redirect:/admin/users-management";
		}
	}

	@PostMapping(value = "/delete/{username}")
	public String deleteUser(Model model, @PathVariable("username") String username) {
		this.userService.deleteUser(username);
		return "redirect:/admin/users-management";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("username", "email", "enabled");
	}

}
