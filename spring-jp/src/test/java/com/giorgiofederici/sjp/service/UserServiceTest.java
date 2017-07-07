package com.giorgiofederici.sjp.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.config.RootApplicationContextConfig;
import com.giorgiofederici.sjp.config.WebApplicationContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootApplicationContextConfig.class, WebApplicationContextConfig.class })
@WebAppConfiguration
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userservice;

	// @Test
	// public void createUserTest() {
	//
	// SignUpForm signUpForm = new SignUpForm();
	// signUpForm.setEmail("testuser@email.com");
	// signUpForm.setUsername("testuser");
	// signUpForm.setPassword("testuser");
	// signUpForm.setMatchingPassword("testuser");
	//
	// this.userservice.createUser(signUpForm);
	//
	// }

	// @Test
	// public void getPaginatedUsers() {
	//
	// List<User> users = this.userservice.getPaginatedUsers(4, 10);
	// for (User user : users) {
	// System.out.println(user.getUsername());
	// }
	//
	// }

}