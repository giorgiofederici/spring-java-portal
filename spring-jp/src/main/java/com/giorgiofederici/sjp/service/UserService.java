package com.giorgiofederici.sjp.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.form.SignUpForm;
import com.giorgiofederici.sjp.domain.form.UserForm;

public interface UserService {

	public void createUser(SignUpForm signUpForm);

	public User getUserByUsername(String username);

	// public User getEnabledUser(String username);

	@Secured("ROLE_ADMIN")
	public List<User> findAll();

	@Secured("ROLE_ADMIN")
	public List<User> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	@Secured("ROLE_ADMIN")
	public int getTotalUsersNumber();

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void updateUser(UserForm userForm);

	@Secured("ROLE_ADMIN")
	public void deleteUser(String username);
}
