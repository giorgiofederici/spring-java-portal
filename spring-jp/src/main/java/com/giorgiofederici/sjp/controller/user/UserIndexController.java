package com.giorgiofederici.sjp.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.giorgiofederici.sjp.domain.entity.UserProfileImage;
import com.giorgiofederici.sjp.domain.form.UserProfileImageForm;
import com.giorgiofederici.sjp.service.UserService;
import com.giorgiofederici.sjp.validator.FileValidator;

@Controller
@RequestMapping(value = "/user/index")
public class UserIndexController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileValidator fileValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String getUserIndex(Model model) throws UnsupportedEncodingException {
		UserProfileImageForm userProfileImageForm = new UserProfileImageForm();
		model.addAttribute("userProfileImageForm", userProfileImageForm);

		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		com.giorgiofederici.sjp.domain.entity.User user = this.userService.getUserByUsername(username);
		model.addAttribute("user", user);

		UserProfileImage userProfileImage = this.userService.getUserProfileImageByUsername(username);
		byte[] encoded = Base64.encodeBase64(userProfileImage.getContent());
		String base64DataString = new String(encoded, "UTF-8");
		model.addAttribute("userProfileImage", base64DataString);

		return "user/index";
	}

	@PostMapping
	public String handleFileUpload(
			@Valid @ModelAttribute("userProfileImageForm") UserProfileImageForm userProfileImageForm,
			BindingResult bindingResult, Model model) throws IOException {

		if (bindingResult.hasErrors()) {
			return "user/index";
		} else {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			userProfileImageForm.setUsername(username);
			this.userService.insertUserProfileImage(userProfileImageForm);
			return "redirect:/user/index";
		}
	}

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

}
