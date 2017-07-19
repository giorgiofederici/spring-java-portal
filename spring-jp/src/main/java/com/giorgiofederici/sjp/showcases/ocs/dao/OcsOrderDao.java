package com.giorgiofederici.sjp.showcases.ocs.dao;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsAddress;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomer;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsOrder;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsShippingDetail;

public interface OcsOrderDao {

	public void createOcsCustomer(OcsCustomer customer);

	public void createOcsShippingDetail(OcsShippingDetail shippingDetail);

	public void createOrder(OcsOrder order);

	public OcsAddress createOcsAddress(OcsAddress address);
}
