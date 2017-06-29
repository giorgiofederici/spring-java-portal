package com.giorgiofederici.sjp.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.form.SignUpForm;
import com.giorgiofederici.sjp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void createUser(SignUpForm signUpForm) {

		User user = new User();
		user.setUsername(signUpForm.getUsername());
		user.setEmail(signUpForm.getEmail());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(signUpForm.getPassword());
		user.setPassword(password);

		user.setEnabled(false);

		Set<Authority> authorities = new HashSet<Authority>();
		Authority authority = new Authority(user, "ROLE_USER");
		authorities.add(authority);

		user.setAuthorities(authorities);

		this.userDao.createUser(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return this.userDao.getUserByUsername(username);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@Override
	public User getEnabledUser(String username) {
		return this.userDao.getEnabledUser(username);
	}

}
