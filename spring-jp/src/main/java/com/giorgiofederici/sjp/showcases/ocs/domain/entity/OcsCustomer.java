package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ocs_customers", catalog = "sjp")
public class OcsCustomer implements Serializable {

	private static final long serialVersionUID = 4820703166990705179L;

	private Integer id;
	private String name;
	private String phoneNumber;
	private OcsAddress billingAddress;

	public OcsCustomer() {
		this.billingAddress = new OcsAddress();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone_number", nullable = false, length = 25)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_address_id", nullable = false)
	public OcsAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(OcsAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

}
