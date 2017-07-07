package com.giorgiofederici.sjp.social;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;

import com.giorgiofederici.sjp.dao.UserConnectionDao;
import com.giorgiofederici.sjp.domain.entity.UserConnection;

public class SjpHibernateUsersConnectionRepository implements UsersConnectionRepository {

	@Autowired
	private UserConnectionDao userConnectionDao;

	private ConnectionSignUp connectionSignUp;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final TextEncryptor textEncryptor;

	public SjpHibernateUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;

	}

	@Override
	public ConnectionRepository createConnectionRepository(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId cannot be null");
		}
		return new SjpHibernateConnectionRepository(userId, connectionFactoryLocator, textEncryptor);
	}

	public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
		this.connectionSignUp = connectionSignUp;
	}

	@Override
	public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {

		List<UserConnection> userConnections = this.userConnectionDao.findUserIdsConnectedTo(providerId,
				providerUserIds);

		Set<String> localUserIds = new HashSet<String>();

		for (UserConnection userConnection : userConnections) {
			localUserIds.add(userConnection.getUserId());
		}

		return localUserIds;
	}

	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {

		ConnectionKey key = connection.getKey();

		List<UserConnection> userConnections = this.userConnectionDao.findUserIdsWithConnection(key);

		List<String> localUserIds = new ArrayList<String>();

		for (UserConnection userConnection : userConnections) {
			localUserIds.add(userConnection.getUserId());
		}

		if (localUserIds.size() == 0 && connectionSignUp != null) {
			String newUserId = connectionSignUp.execute(connection);
			if (newUserId != null) {
				createConnectionRepository(newUserId).addConnection(connection);
				return Arrays.asList(newUserId);
			}
		}
		return localUserIds;
	}

}
