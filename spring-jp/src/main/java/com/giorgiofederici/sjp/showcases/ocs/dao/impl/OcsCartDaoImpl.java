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

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCartDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCartItem;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCartItem_;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCart_;

@Repository
@Transactional
public class OcsCartDaoImpl implements OcsCartDao {

	private final static int ID_COLUMN_INDEX = 0;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OcsCart createCart(OcsCart cart) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cart);
		return cart;
	}

	@Override
	public OcsCart getCartById(String cartId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCart> criteria = criteriaBuilder.createQuery(OcsCart.class);
		Root<OcsCart> root = criteria.from(OcsCart.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(OcsCart_.id), cartId));
		return session.createQuery(criteria).uniqueResult();
	}

	@Override
	public List<OcsCart> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCart> criteria = criteriaBuilder.createQuery(OcsCart.class);
		Root<OcsCart> root = criteria.from(OcsCart.class);
		criteria.select(root);
		return session.createQuery(criteria).list();
	}

	@Override
	public List<OcsCart> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<OcsCart> criteriaQuery = criteriaBuilder.createQuery(OcsCart.class);
		Root<OcsCart> root = criteriaQuery.from(OcsCart.class);
		CriteriaQuery<OcsCart> select = criteriaQuery.select(root);

		// Order criteria
		if (orderColumn == ID_COLUMN_INDEX) {
			if (orderDir.equalsIgnoreCase("asc")) {
				select.orderBy(criteriaBuilder.asc(root.get(OcsCart_.id)));
			} else if (orderDir.equalsIgnoreCase("desc")) {
				select.orderBy(criteriaBuilder.desc(root.get(OcsCart_.id)));
			}
		}

		TypedQuery<OcsCart> typedQuery = session.createQuery(select);

		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(length);

		return typedQuery.getResultList();
	}

	@Override
	public int getTotalCartItemsNumber(String cartId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OcsCartItem> criteriaQuery = criteriaBuilder.createQuery(OcsCartItem.class);
		Root<OcsCartItem> root = criteriaQuery.from(OcsCartItem.class);
		criteriaQuery.select(root);
		Predicate predicateCartId = criteriaBuilder.equal(root.get("cartItemPrimaryKey").get("cart").get("id"), cartId);
		criteriaQuery.where(predicateCartId);
		List<OcsCartItem> cartItems = session.createQuery(criteriaQuery).list();

		int totalQuantity = 0;

		if (cartItems != null && !cartItems.isEmpty()) {
			for (OcsCartItem cartItem : cartItems) {
				int tmpQuantity = cartItem.getQuantity();
				totalQuantity += tmpQuantity;
			}
		} else {
			totalQuantity = 0;
		}

		return totalQuantity;

	}

	@Override
	public void updateCart(OcsCart cart) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsCart> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsCart.class);
		Root<OcsCart> root = criteriaUpdate.from(OcsCart.class);
		Predicate predicateCartId = criteriaBuilder.equal(root.get(OcsCart_.id), cart.getId());
		criteriaUpdate.where(predicateCartId);
		session.createQuery(criteriaUpdate).executeUpdate();

	}

	@Override
	public void deleteCart(String cartId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsCartItem> criteriaDeleteCartItem = criteriaBuilder.createCriteriaDelete(OcsCartItem.class);
		Root<OcsCartItem> rootCartItem = criteriaDeleteCartItem.from(OcsCartItem.class);
		Predicate predicateCartItemCartId = criteriaBuilder
				.equal(rootCartItem.get("cartItemPrimaryKey").get("cart").get("id"), cartId);
		criteriaDeleteCartItem.where(predicateCartItemCartId);
		session.createQuery(criteriaDeleteCartItem).executeUpdate();

		CriteriaDelete<OcsCart> criteriaDeleteCart = criteriaBuilder.createCriteriaDelete(OcsCart.class);
		Root<OcsCart> rootCart = criteriaDeleteCart.from(OcsCart.class);
		Predicate predicateCartId = criteriaBuilder.equal(rootCart.get(OcsCart_.id), cartId);
		criteriaDeleteCart.where(predicateCartId);
		session.createQuery(criteriaDeleteCart).executeUpdate();
	}

	@Override
	public void clearCart(String cartId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsCartItem> criteriaDeleteCartItem = criteriaBuilder.createCriteriaDelete(OcsCartItem.class);
		Root<OcsCartItem> rootCartItem = criteriaDeleteCartItem.from(OcsCartItem.class);
		Predicate predicateCartItemCartId = criteriaBuilder
				.equal(rootCartItem.get("cartItemPrimaryKey").get("cart").get("id"), cartId);
		criteriaDeleteCartItem.where(predicateCartItemCartId);
		session.createQuery(criteriaDeleteCartItem).executeUpdate();
	}

	@Override
	public void createCartItem(OcsCartItem cartItem) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cartItem);
	}

	@Override
	public void updateCartItem(OcsCartItem cartItem) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsCartItem> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsCartItem.class);
		Root<OcsCartItem> root = criteriaUpdate.from(OcsCartItem.class);
		Predicate predicateCartItemId = criteriaBuilder.equal(root.get("cartItemPrimaryKey").get("cartItemId"),
				cartItem.getCartItemPrimaryKey().getCartItemId());
		Predicate predicateCartId = criteriaBuilder.equal(root.get("cartItemPrimaryKey").get("cart"),
				cartItem.getCartItemPrimaryKey().getCart());
		criteriaUpdate.set(root.get("product").get("id"), cartItem.getProduct().getId());
		criteriaUpdate.where(criteriaBuilder.and(predicateCartItemId, predicateCartId));
		session.createQuery(criteriaUpdate).executeUpdate();

	}

	@Override
	public void removeItem(String cartId, Integer productId) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<OcsCartItem> criteriaDeleteCartItem = criteriaBuilder.createCriteriaDelete(OcsCartItem.class);
		Root<OcsCartItem> rootCartItem = criteriaDeleteCartItem.from(OcsCartItem.class);
		Predicate predicateCartItemCartId = criteriaBuilder
				.equal(rootCartItem.get("cartItemPrimaryKey").get("cart").get("id"), cartId);
		Predicate predicateCartItemProductId = criteriaBuilder.equal(rootCartItem.get("product").get("id"), productId);
		criteriaDeleteCartItem.where(criteriaBuilder.and(predicateCartItemCartId, predicateCartItemProductId));
		session.createQuery(criteriaDeleteCartItem).executeUpdate();
	}

	@Override
	public void updateCartItemQuantity(OcsCartItem cartItem) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<OcsCartItem> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OcsCartItem.class);
		Root<OcsCartItem> root = criteriaUpdate.from(OcsCartItem.class);
		Predicate predicateCartId = criteriaBuilder.equal(root.get("cartItemPrimaryKey").get("cart"),
				cartItem.getCartItemPrimaryKey().getCart());
		Predicate predicateCartItemProductId = criteriaBuilder.equal(root.get("product").get("id"),
				cartItem.getProduct().getId());
		criteriaUpdate.set(root.get(OcsCartItem_.quantity), cartItem.getQuantity());
		criteriaUpdate.where(criteriaBuilder.and(predicateCartId, predicateCartItemProductId));
		session.createQuery(criteriaUpdate).executeUpdate();
	}

}
