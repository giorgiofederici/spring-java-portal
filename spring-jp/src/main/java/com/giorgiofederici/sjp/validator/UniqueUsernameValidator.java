package com.giorgiofederici.sjp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.exception.UserNotFoundException;
import com.giorgiofederici.sjp.service.UserService;
import com.giorgiofederici.sjp.validator.annotation.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = null;

		try {
			if (value != null && !value.isEmpty()) {
				user = userService.getUserByUsername(value);
			}
		} catch (UserNotFoundException e) {
			return true;
		}

		if (user != null) {
			return false;
		}

		return true;

	}

}
