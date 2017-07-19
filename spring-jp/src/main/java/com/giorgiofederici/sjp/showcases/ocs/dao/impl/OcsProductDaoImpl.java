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

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsProductDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct_;

@Repository
@Transactional
public class OcsProductDaoImpl implements OcsProductDao {

	private final static int ID_COLUMN_INDEX = 0;
	private final static int CATEGORY_COLUMN_INDEX = 1;
	private final static int NAME_COLUMN_INDEX = 2;
	private final static int PRICE_COLUMN_INDEX = 3;
	private final static int STOCK_COLUMN_INDEX = 4;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createProduct(OcsProduct product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
	}

	@Override
	public OcsProduct getProductById(Integer productId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsProduct> criteria = criteriaBuilder.createQuery(OcsProduct.class);
		Root<OcsProduct> root = criteria.from(OcsProduct.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(OcsProduct_.id), productId));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public List<OcsProduct> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsProduct> criteria = criteriaBuilder.createQuery(OcsProduct.class);
		Root<OcsProduct> root = criteria.from(OcsProduct.class);
		criteria.select(root);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<OcsProduct> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<OcsProduct> criteriaQuery = criteriaBuilder.createQuery(OcsProduct.class);
		Root<OcsProduct> root = criteriaQuery.from(OcsProduct.class);
		CriteriaQuery<OcsProduct> select = criteriaQuery.select(root);

		// Order criteria
		if (orderColumn == ID_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsProduct_.id)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsProduct_.id)));
			}
		} else if (orderColumn == NAME_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsProduct_.name)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsProduct_.name)));
			}
		} else if (orderColumn == PRICE_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsProduct_.price)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsProduct_.price)));
			}
		} else if (orderColumn == STOCK_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsProduct_.stock)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsProduct_.stock)));
			}
		}

		// Search criteria
		if (searchValue != null && !searchValue.trim().isEmpty()) {
			Predicate predicateProductName = criteriaBuilder.equal(root.get(OcsProduct_.name), searchValue);
			Predicate predicateProductPrice = criteriaBuilder.equal(root.get(OcsProduct_.price), searchValue);
			Predicate predicateProductStock = criteriaBuilder.equal(root.get(OcsProduct_.stock), searchValue);
			select.where(predicateProductName, predicateProductPrice, predicateProductStock);
		}

		TypedQuery<OcsProduct> typedQuery = session.createQuery(select);

		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(length);

		return typedQuery.getResultList();
	}

	@Override
	public int getTotalProductsNumber() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(OcsProduct.class)));
		Long count = session.createQuery(countQuery).getSingleResult();

		return count.intValue();
	}

	@Override
	public void updateProduct(OcsProduct product) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsProduct> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsProduct.class);
		Root<OcsProduct> root = criteriaUpdate.from(OcsProduct.class);
		Predicate predicateProductId = criteriaBuilder.equal(root.get(OcsProduct_.id), product.getId());
		criteriaUpdate.set(root.get(OcsProduct_.name), product.getName());
		criteriaUpdate.set(root.get(OcsProduct_.price), product.getPrice());
		criteriaUpdate.set(root.get(OcsProduct_.stock), product.getStock());
		criteriaUpdate.where(predicateProductId);
		session.createQuery(criteriaUpdate).executeUpdate();

	}

	@Override
	public void deleteProduct(Integer productId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsProduct> criteriaDelete = criteriaBuilder.createCriteriaDelete(OcsProduct.class);
		Root<OcsProduct> root = criteriaDelete.from(OcsProduct.class);
		Predicate predicateProductId = criteriaBuilder.equal(root.get(OcsProduct_.id), productId);
		criteriaDelete.where(predicateProductId);
		session.createQuery(criteriaDelete).executeUpdate();

	}

}
