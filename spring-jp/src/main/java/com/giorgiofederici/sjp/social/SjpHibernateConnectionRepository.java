package com.giorgiofederici.sjp.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NoSuchConnectionException;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.giorgiofederici.sjp.dao.UserConnectionDao;
import com.giorgiofederici.sjp.domain.entity.UserConnection;

public class SjpHibernateConnectionRepository implements ConnectionRepository {

	@Autowired
	private UserConnectionDao userConnectionDao;

	private final String userId;
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private final TextEncryptor textEncryptor;

	public SjpHibernateConnectionRepository(String userId, ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.userId = userId;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	@Transactional
	@Override
	public void addConnection(Connection<?> connection) {

		ConnectionData connectionData = connection.createData();

		UserConnection userConnection = new UserConnection();

		String providerId = connectionData.getProviderId();
		String providerUserId = connectionData.getProviderUserId();

		userConnection.setUserId(userId);
		userConnection.setProviderId(providerId);
		userConnection.setProviderUserId(providerUserId);
		userConnection.setRank(this.userConnectionDao.getNextAvailableRank(userId, providerId, providerUserId));
		userConnection.setDisplayName(connectionData.getDisplayName());
		userConnection.setProfileUrl(connectionData.getProfileUrl());
		userConnection.setImgUrl(connectionData.getImageUrl());
		userConnection.setAccessToken(connectionData.getAccessToken());
		userConnection.setSecret(connectionData.getSecret());
		userConnection.setRefreshToken(connectionData.getRefreshToken());
		userConnection.setExpireTime(connectionData.getExpireTime());

		this.userConnectionDao.createUserConnection(userConnection);
	}

	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {

		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

		Set<String> registeredProviderIds = connectionFactoryLocator.registeredProviderIds();
		for (String registeredProviderId : registeredProviderIds) {
			connections.put(registeredProviderId, Collections.<Connection<?>>emptyList());
		}

		List<UserConnection> userConnectionList = this.userConnectionDao.findAllUserConnections(userId);

		for (UserConnection userConnection : userConnectionList) {
			String providerId = userConnection.getProviderId();
			if (connections.get(providerId).isEmpty()) {
				connections.put(providerId, new LinkedList<Connection<?>>());
			}
			connections.add(providerId, this.connectionMapper.mapConnectionData(userConnection));
		}

		return connections;
	}

	@Override
	public List<Connection<?>> findConnections(String providerId) {

		List<Connection<?>> connections = new ArrayList<Connection<?>>();
		List<UserConnection> userConnectionList = this.userConnectionDao.findConnections(userId, providerId);

		for (UserConnection userConnection : userConnectionList) {
			connections.add(this.connectionMapper.mapConnectionData(userConnection));
		}

		return connections;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		List<?> connections = findConnections(getProviderId(apiType));
		return (List<Connection<A>>) connections;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUsers) {

		if (providerUsers == null || providerUsers.isEmpty()) {
			throw new IllegalArgumentException("Unable to execute find: no providerUsers provided");
		}

		List<Connection<?>> resultList = new ArrayList<Connection<?>>();
		List<UserConnection> userConnectionList = this.userConnectionDao.findConnectionsToUsers(userId, providerUsers);

		for (UserConnection userConnection : userConnectionList) {
			resultList.add(this.connectionMapper.mapConnectionData(userConnection));
		}

		MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<String, Connection<?>>();
		for (Connection<?> connection : resultList) {
			String providerId = connection.getKey().getProviderId();
			List<String> userIds = providerUsers.get(providerId);
			List<Connection<?>> connections = connectionsForUsers.get(providerId);
			if (connections == null) {
				connections = new ArrayList<Connection<?>>(userIds.size());
				for (int i = 0; i < userIds.size(); i++) {
					connections.add(null);
				}
				connectionsForUsers.put(providerId, connections);
			}
			String providerUserId = connection.getKey().getProviderUserId();
			int connectionIndex = userIds.indexOf(providerUserId);
			connections.set(connectionIndex, connection);
		}
		return connectionsForUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) findPrimaryConnection(providerId);
	}

	@Override
	public Connection<?> getConnection(ConnectionKey connectionKey) {

		UserConnection userConnection = this.userConnectionDao.getConnection(userId, connectionKey.getProviderId(),
				connectionKey.getProviderUserId());

		if (userConnection == null) {
			throw new NoSuchConnectionException(connectionKey);
		}

		return this.connectionMapper.mapConnectionData(userConnection);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) getConnection(new ConnectionKey(providerId, providerUserId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
		if (connection == null) {
			throw new NotConnectedException(providerId);
		}
		return connection;
	}

	@Override
	public void removeConnection(ConnectionKey connectionKey) {
		this.userConnectionDao.removeConnection(userId, connectionKey.getProviderId(),
				connectionKey.getProviderUserId());
	}

	@Override
	public void removeConnections(String providerId) {
		this.userConnectionDao.removeConnections(userId, providerId);

	}

	@Override
	public void updateConnection(Connection<?> connection) {
		ConnectionData data = connection.createData();
		this.userConnectionDao.updateConnection(userId, data, encrypt(data.getAccessToken()), encrypt(data.getSecret()),
				encrypt(data.getRefreshToken()));

	}

	private Connection<?> findPrimaryConnection(String providerId) {

		List<Connection<?>> connections = new ArrayList<Connection<?>>();
		List<UserConnection> userConnectionList = this.userConnectionDao.findConnections(userId, providerId);

		for (UserConnection userConnection : userConnectionList) {
			connections.add(this.connectionMapper.mapConnectionData(userConnection));
		}

		if (connections.size() > 0) {
			return connections.get(0);
		} else {
			return null;
		}
	}

	private <A> String getProviderId(Class<A> apiType) {
		return connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
	}

	private String encrypt(String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}

	private final ServiceProviderConnectionMapper connectionMapper = new ServiceProviderConnectionMapper();

	private final class ServiceProviderConnectionMapper {

		public Connection<?> mapConnectionData(UserConnection userConnection) {

			ConnectionData connectionData = new ConnectionData(userConnection.getProviderId(),
					userConnection.getProviderUserId(), userConnection.getDisplayName(), userConnection.getProfileUrl(),
					userConnection.getImgUrl(), decrypt(userConnection.getAccessToken()),
					decrypt(userConnection.getSecret()), decrypt(userConnection.getRefreshToken()),
					userConnection.getExpireTime());

			ConnectionFactory<?> connectionFactory = connectionFactoryLocator
					.getConnectionFactory(connectionData.getProviderId());
			return connectionFactory.createConnection(connectionData);
		}

		private String decrypt(String encryptedText) {
			return encryptedText != null ? textEncryptor.decrypt(encryptedText) : encryptedText;
		}

		private Long expireTime(long expireTime) {
			return expireTime == 0 ? null : expireTime;
		}
	}

}
