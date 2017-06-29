package com.giorgiofederici.sjp.dao.impl;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.entity.User_;
import com.giorgiofederici.sjp.exception.AuthoritiesNotFoundException;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		session.persist(user);

		Set<Authority> authorities = user.getAuthorities();
		if (authorities == null || authorities.isEmpty()) {
			throw new AuthoritiesNotFoundException();
		}

		for (Authority auth : authorities) {
			session.persist(auth);
		}

		transaction.commit();
		session.close();
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(User_.username), username));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public User getEnabledUser(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Predicate predicateUserUsername = criteriaBuilder.equal(root.get(User_.username), username);
		Predicate predicateUserEnabled = criteriaBuilder.equal(root.get(User_.enabled), true);
		criteria.where(criteriaBuilder.and(predicateUserUsername, predicateUserEnabled));
		return session.createQuery(criteria).uniqueResult();
	}

}
