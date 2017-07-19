package com.giorgiofederici.sjp.dao;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.entity.UserProfileImage;

public interface UserDao {

	public void createUser(User user);

	public User getUserByUsername(String username);

	@Secured("ROLE_ADMIN")
	public List<User> findAll();

	@Secured("ROLE_ADMIN")
	public List<User> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	@Secured("ROLE_ADMIN")
	public int getTotalUsersNumber();

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void updateUser(User user);

	@Secured("ROLE_ADMIN")
	public void deleteUser(String username);

	public void insertUserProfileImage(UserProfileImage userProfileImage);

	public UserProfileImage getUserProfileImageByUsername(String username);

	public void updateUserProfileImage(UserProfileImage userProfileImage);

}
