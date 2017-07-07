package com.giorgiofederici.sjp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.dao.UserConnectionDao;
import com.giorgiofederici.sjp.domain.entity.UserConnection;

@Service
public class SjpSocialConnectionSignUp implements ConnectionSignUp {

	@Autowired
	private UserConnectionDao userConnectionDao;

	@Override
	public String execute(Connection<?> connection) {

		ConnectionData connectionData = connection.createData();

		UserConnection userConnection = new UserConnection();

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		String providerId = connectionData.getProviderId();
		String providerUserId = connectionData.getProviderUserId();

		userConnection.setUserId(userId);
		userConnection.setProviderId(providerId);
		userConnection.setProviderUserId(providerUserId);
		userConnection.setRank(userConnectionDao.getNextAvailableRank(userId, providerId, providerUserId));
		userConnection.setDisplayName(connectionData.getDisplayName());
		userConnection.setProfileUrl(connectionData.getProfileUrl());
		userConnection.setImgUrl(connectionData.getImageUrl());
		userConnection.setAccessToken(connectionData.getAccessToken());
		userConnection.setSecret(connectionData.getSecret());
		userConnection.setRefreshToken(connectionData.getRefreshToken());
		userConnection.setExpireTime(connectionData.getExpireTime());

		userConnectionDao.createUserConnection(userConnection);

		return userId;
	}

}
