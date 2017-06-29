package com.giorgiofederici.sjp.dao;

import com.giorgiofederici.sjp.domain.entity.User;

public interface UserDao {

	public void createUser(User user);

	public User getUserByUsername(String username);

	public User getEnabledUser(String username);

}
