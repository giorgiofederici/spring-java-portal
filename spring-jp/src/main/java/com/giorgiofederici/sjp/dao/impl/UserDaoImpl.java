package com.giorgiofederici.sjp.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.domain.entity.UserProfileImage;
import com.giorgiofederici.sjp.domain.entity.UserProfileImage_;
import com.giorgiofederici.sjp.domain.entity.User_;
import com.giorgiofederici.sjp.exception.AuthoritiesNotFoundException;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private final static int USERNAME_COLUMN_INDEX = 0;
	private final static int EMAIL_COLUMN_INDEX = 1;
	private final static int ENABLED_COLUMN_INDEX = 2;

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
	public List<User> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<User> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		CriteriaQuery<User> select = criteriaQuery.multiselect(root.<User>get("username"), root.<User>get("email"),
				root.<User>get("enabled"));

		// Order criteria
		if (orderColumn == USERNAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(User_.username)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(User_.username)));
			}
		} else if (orderColumn == EMAIL_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(User_.email)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(User_.email)));
			}
		} else if (orderColumn == ENABLED_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(User_.enabled)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(User_.enabled)));
			}
		}

		// Search criteria
		if (searchValue != null && !searchValue.trim().isEmpty()) {
			Predicate predicateUserUsername = criteriaBuilder.equal(root.get(User_.username), searchValue);
			Predicate predicateUserEmail = criteriaBuilder.equal(root.get(User_.email), searchValue);
			select.where(criteriaBuilder.or(predicateUserUsername, predicateUserEmail));
		}

		TypedQuery<User> typedQuery = session.createQuery(select);

		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(length);

		return typedQuery.getResultList();

	}

	@Override
	public int getTotalUsersNumber() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(User.class)));
		Long count = session.createQuery(countQuery).getSingleResult();

		return count.intValue();
	}

	@Override
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
		Root<User> root = criteriaUpdate.from(User.class);
		Predicate predicateUserUsername = criteriaBuilder.equal(root.get(User_.username), user.getUsername());
		criteriaUpdate.set(root.get(User_.email), user.getEmail());
		criteriaUpdate.set(root.get(User_.enabled), user.isEnabled());
		criteriaUpdate.where(predicateUserUsername);
		session.createQuery(criteriaUpdate).executeUpdate();
	}

	@Override
	public void deleteUser(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
		Root<User> root = criteriaDelete.from(User.class);
		Predicate predicateUserUsername = criteriaBuilder.equal(root.get(User_.username), username);
		criteriaDelete.where(predicateUserUsername);
		session.createQuery(criteriaDelete).executeUpdate();

	}

	@Override
	public void insertUserProfileImage(UserProfileImage userProfileImage) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userProfileImage);
	}

	@Override
	public UserProfileImage getUserProfileImageByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserProfileImage> criteria = criteriaBuilder.createQuery(UserProfileImage.class);
		Root<UserProfileImage> root = criteria.from(UserProfileImage.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(UserProfileImage_.username), username));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public void updateUserProfileImage(UserProfileImage userProfileImage) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<UserProfileImage> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(UserProfileImage.class);
		Root<UserProfileImage> root = criteriaUpdate.from(UserProfileImage.class);
		Predicate predicateUserProfileImageUsername = criteriaBuilder.equal(root.get(UserProfileImage_.username),
				userProfileImage.getUsername());
		criteriaUpdate.set(root.get(UserProfileImage_.name), userProfileImage.getName());
		criteriaUpdate.set(root.get(UserProfileImage_.type), userProfileImage.getType());
		criteriaUpdate.set(root.get(UserProfileImage_.content), userProfileImage.getContent());
		criteriaUpdate.where(predicateUserProfileImageUsername);
		session.createQuery(criteriaUpdate).executeUpdate();
	}

}