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

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCategoryDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory_;

@Repository
@Transactional
public class OcsCategoryDaoImpl implements OcsCategoryDao {

	private final static int ID_COLUMN_INDEX = 0;
	private final static int NAME_COLUMN_INDEX = 1;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createCategory(OcsCategory category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(category);
	}

	@Override
	public OcsCategory getCategoryById(Integer categoryId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCategory> criteria = criteriaBuilder.createQuery(OcsCategory.class);
		Root<OcsCategory> root = criteria.from(OcsCategory.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(OcsCategory_.id), categoryId));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public List<OcsCategory> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCategory> criteria = criteriaBuilder.createQuery(OcsCategory.class);
		Root<OcsCategory> root = criteria.from(OcsCategory.class);
		criteria.select(root);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<OcsCategory> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<OcsCategory> criteriaQuery = criteriaBuilder.createQuery(OcsCategory.class);
		Root<OcsCategory> root = criteriaQuery.from(OcsCategory.class);
		CriteriaQuery<OcsCategory> select = criteriaQuery.select(root);

		// Order criteria
		if (orderColumn == ID_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCategory_.id)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCategory_.id)));
			}
		} else if (orderColumn == NAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCategory_.name)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCategory_.name)));
			}
		}

		// Search criteria
		if (searchValue != null && !searchValue.trim().isEmpty()) {
			Predicate predicateCategoryName = criteriaBuilder.equal(root.get(OcsCategory_.name), searchValue);
			select.where(predicateCategoryName);
		}

		TypedQuery<OcsCategory> typedQuery = session.createQuery(select);

		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(length);

		return typedQuery.getResultList();
	}

	@Override
	public int getTotalCategoriesNumber() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(OcsCategory.class)));
		Long count = session.createQuery(countQuery).getSingleResult();

		return count.intValue();
	}

	@Override
	public void updateCategory(OcsCategory category) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsCategory> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsCategory.class);
		Root<OcsCategory> root = criteriaUpdate.from(OcsCategory.class);
		Predicate predicateCategoryId = criteriaBuilder.equal(root.get(OcsCategory_.id), category.getId());
		criteriaUpdate.set(root.get(OcsCategory_.name), category.getName());
		criteriaUpdate.where(predicateCategoryId);
		session.createQuery(criteriaUpdate).executeUpdate();

	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsCategory> criteriaDelete = criteriaBuilder.createCriteriaDelete(OcsCategory.class);
		Root<OcsCategory> root = criteriaDelete.from(OcsCategory.class);
		Predicate predicateCategoryId = criteriaBuilder.equal(root.get(OcsCategory_.id), categoryId);
		criteriaDelete.where(predicateCategoryId);
		session.createQuery(criteriaDelete).executeUpdate();

	}

}
