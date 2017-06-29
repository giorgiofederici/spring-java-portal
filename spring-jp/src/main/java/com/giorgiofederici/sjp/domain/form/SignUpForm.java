package com.giorgiofederici.sjp.domain.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.giorgiofederici.sjp.validator.annotation.PasswordMatches;
import com.giorgiofederici.sjp.validator.annotation.UniqueUsername;

@PasswordMatches
public class SignUpForm {

	private String username;
	private String email;
	private String password;
	private String matchingPassword;

	public SignUpForm() {

	}

	public SignUpForm(String username, String email, String password, String matchingPassword) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
	}

	@NotNull(message = "{sjp.signup.form.username.notNull}")
	@NotEmpty(message = "{sjp.signup.form.username.notEmpty}")
	@UniqueUsername
	@Length(max = 50, message = "{sjp.signup.form.username.length}")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull(message = "{sjp.signup.form.email.notNull}")
	@NotEmpty(message = "{sjp.signup.form.email.notEmpty}")
	@Email(message = "{sjp.signup.form.email.email}")
	@Length(max = 50, message = "{sjp.signup.form.email.length}")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull(message = "{sjp.signup.form.password.notNull}")
	@NotEmpty(message = "{sjp.signup.form.password.notEmpty}")
	@Length(max = 50, message = "{sjp.signup.form.password.length}")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
