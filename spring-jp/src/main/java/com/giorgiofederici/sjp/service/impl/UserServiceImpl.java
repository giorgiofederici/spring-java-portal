package com.giorgiofederici.sjp.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.entity.UserProfileImage;
import com.giorgiofederici.sjp.domain.form.SignUpForm;
import com.giorgiofederici.sjp.domain.form.UserForm;
import com.giorgiofederici.sjp.domain.form.UserProfileImageForm;
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

	@Override
	public List<User> findAll() {
		return this.userDao.findAll();
	}

	@Override
	public List<User> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		return this.userDao.findAll(start, length, orderColumn, orderDir, searchValue);
	}

	@Override
	public int getTotalUsersNumber() {
		return this.userDao.getTotalUsersNumber();
	}

	@Override
	public void updateUser(UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setEnabled(userForm.isEnabled());

		this.userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String username) {
		this.userDao.deleteUser(username);
	}

	@Override
	public void insertUserProfileImage(UserProfileImageForm userProfileImageForm) throws IOException {

		String username = userProfileImageForm.getUsername();
		User user = this.getUserByUsername(userProfileImageForm.getUsername());

		UserProfileImage userProfileImage = this.userDao.getUserProfileImageByUsername(username);

		if (userProfileImage == null) {
			userProfileImage = new UserProfileImage();
			userProfileImage.setUsername(userProfileImageForm.getUsername());
			userProfileImage.setName(userProfileImageForm.getFile().getName());
			userProfileImage.setType(userProfileImageForm.getFile().getContentType());
			userProfileImage.setContent(userProfileImageForm.getFile().getBytes());

			userProfileImage.setUser(user);

			this.userDao.insertUserProfileImage(userProfileImage);
		} else {
			userProfileImage.setUsername(userProfileImageForm.getUsername());
			userProfileImage.setName(userProfileImageForm.getFile().getName());
			userProfileImage.setType(userProfileImageForm.getFile().getContentType());
			userProfileImage.setContent(userProfileImageForm.getFile().getBytes());
			userProfileImage.setUser(user);

			this.userDao.updateUserProfileImage(userProfileImage);
		}

	}

	@Override
	public UserProfileImage getUserProfileImageByUsername(String username) {
		return this.userDao.getUserProfileImageByUsername(username);
	}

}
