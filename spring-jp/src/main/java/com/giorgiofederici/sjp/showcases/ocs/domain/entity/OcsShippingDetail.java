package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ocs_shipping_details", catalog = "sjp")
public class OcsShippingDetail implements java.io.Serializable {

	private static final long serialVersionUID = 6956222521875912976L;

	private int id;
	private String name;
	private Date shippingDate;
	private OcsAddress shippingAddress;

	public OcsShippingDetail() {
		this.shippingAddress = new OcsAddress();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "shipping_date", nullable = false)
	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_address_id", nullable = false)
	public OcsAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(OcsAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
