package com.giorgiofederici.sjp.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.config.RootApplicationContextConfig;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@WebAppConfiguration
@Transactional
public class UserDaoTest {

	private final static String TEST_USERNAME = "usertest";

	@Autowired
	private UserDao userDao;

	@Test
	public void createUserTest() {

		User user = new User();
		user.setUsername(TEST_USERNAME);
		user.setEmail("usertest@email.com");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode("usertest");
		user.setPassword(password);
		user.setEnabled(false);

		Set<Authority> authorities = new HashSet<Authority>();
		Authority authority = new Authority(user, "ROLE_USER");
		authorities.add(authority);

		user.setAuthorities(authorities);

		this.userDao.createUser(user);

	}

	// @Test
	// public void getUserByUsernameTest() {
	//
	// User user = this.userDao.getUserByUsername(TEST_USERNAME);
	// Assert.assertEquals(TEST_USERNAME, user.getUsername());
	// }

}
