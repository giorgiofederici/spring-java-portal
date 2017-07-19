package com.giorgiofederici.sjp.showcases.ocs.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsOrderDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsAddress;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomer;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsOrder;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsShippingDetail;

@Repository
@Transactional
public class OcsOrderDaoImpl implements OcsOrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createOcsCustomer(OcsCustomer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer);
	}

	@Override
	public void createOcsShippingDetail(OcsShippingDetail shippingDetail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(shippingDetail);
	}

	@Override
	public void createOrder(OcsOrder order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
	}

	@Override
	public OcsAddress createOcsAddress(OcsAddress address) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(address);
		return address;
	}

}
