package com.giorgiofederici.sjp.validator.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.giorgiofederici.sjp.validator.PasswordMatchesValidator;

@Target(value = { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

	String message() default "{sjp.validation.passwordMatches}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
