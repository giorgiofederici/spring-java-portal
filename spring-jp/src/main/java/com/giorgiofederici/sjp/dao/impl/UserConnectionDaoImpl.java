package com.giorgiofederici.sjp.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.giorgiofederici.sjp.dao.UserConnectionDao;
import com.giorgiofederici.sjp.domain.entity.UserConnection;
import com.giorgiofederici.sjp.domain.entity.UserConnection_;

@Repository
@Transactional
public class UserConnectionDaoImpl implements UserConnectionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createUserConnection(UserConnection userConnection) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userConnection);
	}

	@Override
	public Integer getNextAvailableRank(String userId, String providerId, String providerUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		Predicate predicateUserConnectionProviderUserId = criteriaBuilder
				.equal(root.get(UserConnection_.providerUserId), providerUserId);
		// criteria.select(root.<UserConnection>get("rank"));
		criteria.select(root);
		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId,
				predicateUserConnectionProviderUserId));
		UserConnection userConnection = session.createQuery(criteria).uniqueResult();

		if (userConnection == null || userConnection.getRank() == null) {
			return 0;
		} else {
			return userConnection.getRank() + 1;
		}
	}

	@Override
	public List<UserConnection> findAllUserConnections(String userId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		criteria.select(root);
		criteria.orderBy(criteriaBuilder.asc(root.get(UserConnection_.providerId)),
				criteriaBuilder.asc(root.get(UserConnection_.rank)));
		criteria.where(predicateUserConnectionUserId);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<UserConnection> findConnections(String userId, String providerId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		criteria.select(root);
		criteria.orderBy(criteriaBuilder.asc(root.get(UserConnection_.rank)));
		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId));
		return session.createQuery(criteria).list();
	}

	@Override
	public List<UserConnection> findConnectionsToUsers(String userId, MultiValueMap<String, String> providerUsers) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		criteria.orderBy(criteriaBuilder.asc(root.get(UserConnection_.providerId)),
				criteriaBuilder.asc(root.get(UserConnection_.rank)));
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);

		List<Predicate> predicateUserConnectionProviders = new ArrayList<Predicate>();

		for (Iterator<Entry<String, List<String>>> it = providerUsers.entrySet().iterator(); it.hasNext();) {
			Entry<String, List<String>> entry = it.next();
			String providerId = entry.getKey();

			Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
					providerId);
			Expression<String> tmpProviderUserIdExp = root.get(UserConnection_.providerUserId);
			Predicate predicateUserConnectionProviderUserId = tmpProviderUserIdExp
					.in(root.get(UserConnection_.providerUserId), entry.getValue());
			Predicate tmpPredicateUserConnection = criteriaBuilder.and(predicateUserConnectionProviderId,
					predicateUserConnectionProviderUserId);

			predicateUserConnectionProviders
					.add(criteriaBuilder.or(predicateUserConnectionProviderUserId, tmpPredicateUserConnection));

		}

		Predicate[] predicateOrProviders = predicateUserConnectionProviders.toArray(new Predicate[] {});

		criteria.where(predicateUserConnectionUserId);
		criteria.where(criteriaBuilder.and(predicateOrProviders));

		return session.createQuery(criteria).list();

	}

	@Override
	public UserConnection getConnection(String userId, String providerId, String providerUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		Predicate predicateUserConnectionProviderUserId = criteriaBuilder
				.equal(root.get(UserConnection_.providerUserId), providerUserId);
		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId,
				predicateUserConnectionProviderUserId));
		UserConnection userConnection = session.createQuery(criteria).uniqueResult();

		return userConnection;
	}

	@Override
	public void removeConnection(String userId, String providerId, String providerUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<UserConnection> criteria = criteriaBuilder.createCriteriaDelete(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		Predicate predicateUserConnectionProviderUserId = criteriaBuilder
				.equal(root.get(UserConnection_.providerUserId), providerUserId);
		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId,
				predicateUserConnectionProviderUserId));
		session.createQuery(criteria).executeUpdate();
	}

	@Override
	public void removeConnections(String userId, String providerId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<UserConnection> criteria = criteriaBuilder.createCriteriaDelete(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId));
		session.createQuery(criteria).executeUpdate();
	}

	@Override
	public void updateConnection(String userId, ConnectionData connectionData, String encryptedAccessToken,
			String encryptedSecret, String encryptedRefreshToken) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<UserConnection> criteria = criteriaBuilder.createCriteriaUpdate(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		Predicate predicateUserConnectionUserId = criteriaBuilder.equal(root.get(UserConnection_.userId), userId);
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				connectionData.getProviderId());
		Predicate predicateUserConnectionProviderUserId = criteriaBuilder
				.equal(root.get(UserConnection_.providerUserId), connectionData.getProviderUserId());

		criteria.set(root.get(UserConnection_.displayName), connectionData.getDisplayName());
		criteria.set(root.get(UserConnection_.profileUrl), connectionData.getProfileUrl());
		criteria.set(root.get(UserConnection_.imgUrl), connectionData.getImageUrl());
		criteria.set(root.get(UserConnection_.accessToken), encryptedAccessToken);
		criteria.set(root.get(UserConnection_.secret), encryptedSecret);
		criteria.set(root.get(UserConnection_.refreshToken), encryptedRefreshToken);
		criteria.set(root.get(UserConnection_.expireTime), connectionData.getExpireTime());

		criteria.where(criteriaBuilder.and(predicateUserConnectionUserId, predicateUserConnectionProviderId,
				predicateUserConnectionProviderUserId));

		session.createQuery(criteria).executeUpdate();
	}

	@Override
	public List<UserConnection> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		criteria.select(root.<UserConnection>get("userId"));
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				providerId);
		Expression<String> tmpProviderUserIdExp = root.get(UserConnection_.providerUserId);
		Predicate predicateUserConnectionProviderUserId = tmpProviderUserIdExp
				.in(root.get(UserConnection_.providerUserId), providerUserIds);
		criteria.where(criteriaBuilder.and(predicateUserConnectionProviderId, predicateUserConnectionProviderUserId));
		return session.createQuery(criteria).list();
	}

	@Override
	public List<UserConnection> findUserIdsWithConnection(ConnectionKey key) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserConnection> criteria = criteriaBuilder.createQuery(UserConnection.class);
		Root<UserConnection> root = criteria.from(UserConnection.class);
		criteria.select(root.<UserConnection>get("userId"));
		Predicate predicateUserConnectionProviderId = criteriaBuilder.equal(root.get(UserConnection_.providerId),
				key.getProviderId());
		Predicate predicateUserConnectionProviderUserId = criteriaBuilder
				.equal(root.get(UserConnection_.providerUserId), key.getProviderUserId());
		criteria.where(criteriaBuilder.and(predicateUserConnectionProviderId, predicateUserConnectionProviderUserId));
		return session.createQuery(criteria).list();
	}
}
