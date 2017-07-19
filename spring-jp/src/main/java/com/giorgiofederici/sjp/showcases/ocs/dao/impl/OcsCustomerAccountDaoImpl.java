package com.giorgiofederici.sjp.showcases.ocs.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCustomerAccountDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount_;

@Repository
@Transactional
public class OcsCustomerAccountDaoImpl implements OcsCustomerAccountDao {

	private final static int USERNAME_COLUMN_INDEX = 0;
	private final static int FIRST_NAME_COLUMN_INDEX = 1;
	private final static int LAST_NAME_COLUMN_INDEX = 2;
	private final static int EMAIL_COLUMN_INDEX = 3;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createCustomerAccount(OcsCustomerAccount customerAccount) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customerAccount);
	}

	@Override
	public OcsCustomerAccount getCustomerAccountByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCustomerAccount> criteria = criteriaBuilder.createQuery(OcsCustomerAccount.class);
		Root<OcsCustomerAccount> root = criteria.from(OcsCustomerAccount.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(OcsCustomerAccount_.username), username));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public List<OcsCustomerAccount> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCustomerAccount> criteria = criteriaBuilder.createQuery(OcsCustomerAccount.class);
		Root<OcsCustomerAccount> root = criteria.from(OcsCustomerAccount.class);
		criteria.select(root);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<OcsCustomerAccount> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<OcsCustomerAccount> criteriaQuery = criteriaBuilder.createQuery(OcsCustomerAccount.class);
		Root<OcsCustomerAccount> root = criteriaQuery.from(OcsCustomerAccount.class);
		CriteriaQuery<OcsCustomerAccount> select = criteriaQuery.select(root);

		// Order criteria
		if (orderColumn == USERNAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCustomerAccount_.username)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCustomerAccount_.username)));
			}
		} else if (orderColumn == FIRST_NAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCustomerAccount_.firstName)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCustomerAccount_.firstName)));
			}
		} else if (orderColumn == LAST_NAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCustomerAccount_.lastName)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCustomerAccount_.lastName)));
			}
		} else if (orderColumn == EMAIL_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCustomerAccount_.email)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCustomerAccount_.email)));
			}
		}

		// Search criteria
		if (searchValue != null && !searchValue.trim().isEmpty()) {
			Predicate predicateCustomerAccountUsername = criteriaBuilder.equal(root.get(OcsCustomerAccount_.username),
					searchValue);
			Predicate predicateCustomerAccountFirstName = criteriaBuilder.equal(root.get(OcsCustomerAccount_.firstName),
					searchValue);
			Predicate predicateCustomerAccountLastName = criteriaBuilder.equal(root.get(OcsCustomerAccount_.lastName),
					searchValue);
			Predicate predicateCustomerAccountEmail = criteriaBuilder.equal(root.get(OcsCustomerAccount_.email),
					searchValue);
			select.where(criteriaBuilder.and(predicateCustomerAccountUsername, predicateCustomerAccountFirstName,
					predicateCustomerAccountLastName, predicateCustomerAccountEmail));
		}

		TypedQuery<OcsCustomerAccount> typedQuery = session.createQuery(select);

		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(length);

		return typedQuery.getResultList();
	}

	@Override
	public int getTotalCustomerAccountsNumber() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(OcsCustomerAccount.class)));
		Long count = session.createQuery(countQuery).getSingleResult();

		return count.intValue();
	}

	@Override
	public void updateCustomerAccount(OcsCustomerAccount customerAccount) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsCustomerAccount> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsCustomerAccount.class);
		Root<OcsCustomerAccount> root = criteriaUpdate.from(OcsCustomerAccount.class);
		Predicate predicateCustomerAccountUsername = criteriaBuilder.equal(root.get(OcsCustomerAccount_.username),
				customerAccount.getUser().getUsername());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.firstName), customerAccount.getFirstName());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.lastName), customerAccount.getLastName());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.midName), customerAccount.getFirstName());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.birthDate), customerAccount.getBirthDate());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.address), customerAccount.getAddress());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.email), customerAccount.getEmail());
		criteriaUpdate.set(root.get(OcsCustomerAccount_.mobile), customerAccount.getMobile());

		criteriaUpdate.where(predicateCustomerAccountUsername);
		session.createQuery(criteriaUpdate).executeUpdate();

	}

	@Override
	public void deleteCustomerAccount(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsCustomerAccount> criteriaDelete = criteriaBuilder.createCriteriaDelete(OcsCustomerAccount.class);
		Root<OcsCustomerAccount> root = criteriaDelete.from(OcsCustomerAccount.class);
		Predicate predicateCategoryId = criteriaBuilder.equal(root.get(OcsCustomerAccount_.username), username);
		criteriaDelete.where(predicateCategoryId);
		session.createQuery(criteriaDelete).executeUpdate();

	}

}
