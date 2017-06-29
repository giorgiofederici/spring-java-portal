package com.giorgiofederici.sjp.service;

import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.form.SignUpForm;

public interface UserService {

	public void createUser(SignUpForm signUpForm);

	public User getUserByUsername(String username);

	public User getEnabledUser(String username);

}
