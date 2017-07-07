package com.giorgiofederici.sjp.dao;

import java.util.List;
import java.util.Set;

import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.util.MultiValueMap;

import com.giorgiofederici.sjp.domain.entity.UserConnection;

public interface UserConnectionDao {

	public void createUserConnection(UserConnection userConnection);

	public Integer getNextAvailableRank(String userId, String providerId, String providerUserId);

	public List<UserConnection> findAllUserConnections(String userId);

	public List<UserConnection> findConnections(String userId, String providerId);

	public List<UserConnection> findConnectionsToUsers(String userId, MultiValueMap<String, String> providerUsers);

	public UserConnection getConnection(String userId, String providerId, String providerUserId);

	public void removeConnection(String userId, String providerId, String providerUserId);

	public void removeConnections(String userId, String providerId);

	public void updateConnection(String userId, ConnectionData connectionData, String encryptedAccessToken,
			String encryptedSecret, String encryptedRefreshToken);

	public List<UserConnection> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds);

	public List<UserConnection> findUserIdsWithConnection(ConnectionKey key);
}
