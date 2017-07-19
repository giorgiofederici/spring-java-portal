package com.giorgiofederici.sjp.showcases.ocs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsOrderDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsAddress;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomer;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsOrder;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsShippingDetail;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCartService;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsOrderService;

@Service
@Transactional
public class OcsOrderServiceImpl implements OcsOrderService {

	@Autowired
	private OcsCartService cartService;

	@Autowired
	private OcsOrderDao orderDao;

	@Override
	public int saveOrder(OcsOrder order) {

		OcsCustomer customer = order.getCustomer();
		OcsAddress customerAddress = customer.getBillingAddress();
		customerAddress = this.orderDao.createOcsAddress(customerAddress);
		customer.setBillingAddress(customerAddress);
		this.orderDao.createOcsCustomer(customer);

		OcsShippingDetail shippingDetail = order.getShippingDetail();
		OcsAddress shippingAddress = shippingDetail.getShippingAddress();
		shippingAddress = this.orderDao.createOcsAddress(shippingAddress);
		shippingDetail.setShippingAddress(shippingAddress);
		this.orderDao.createOcsShippingDetail(shippingDetail);

		this.orderDao.createOrder(order);
		this.cartService.clearCart(order.getCart().getId());

		return order.getId();
	}

}
