package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ocs_orders", catalog = "sjp")
public class OcsOrder implements java.io.Serializable {

	private static final long serialVersionUID = 8499413148464835062L;

	private Integer id;
	private OcsCart cart;
	private OcsCustomer customer;
	private OcsShippingDetail shippingDetail;

	public OcsOrder() {
		this.customer = new OcsCustomer();
		this.shippingDetail = new OcsShippingDetail();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", nullable = false)
	public OcsCart getCart() {
		return cart;
	}

	public void setCart(OcsCart cart) {
		this.cart = cart;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public OcsCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(OcsCustomer customer) {
		this.customer = customer;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_detail_id", nullable = false)
	public OcsShippingDetail getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(OcsShippingDetail shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

}
