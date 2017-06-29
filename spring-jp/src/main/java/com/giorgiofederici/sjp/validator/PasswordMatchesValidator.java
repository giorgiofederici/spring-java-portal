package com.giorgiofederici.sjp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.giorgiofederici.sjp.domain.form.SignUpForm;
import com.giorgiofederici.sjp.validator.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		SignUpForm signUpForm = (SignUpForm) obj;
		boolean isValid = signUpForm.getPassword().equals(signUpForm.getMatchingPassword());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{sjp.validation.passwordMatches}")
					.addPropertyNode("matchingPassword").addConstraintViolation();
		}

		return isValid;
	}

}
