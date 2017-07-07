package com.giorgiofederici.sjp.domain.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	private String username;
	private String email;
	private boolean enabled;

	public UserForm() {

	}

	public UserForm(String username, String email, boolean enabled) {
		this.username = username;
		this.email = email;
		this.enabled = enabled;
	}

	@NotNull(message = "{sjp.admin.users.management.edit.form.username.notNull}")
	@NotEmpty(message = "{sjp.admin.users.management.edit.form.username.notEmpty}")
	@Length(max = 50, message = "{sjp.admin.users.management.edit.form.username.length}")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull(message = "{sjp.admin.users.management.edit.form.email.notNull}")
	@NotEmpty(message = "{sjp.admin.users.management.edit.form.email.notEmpty}")
	@Email(message = "{sjp.admin.users.management.edit.form.email.email}")
	@Length(max = 50, message = "{sjp.admin.users.management.edit.form.email.length}")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
