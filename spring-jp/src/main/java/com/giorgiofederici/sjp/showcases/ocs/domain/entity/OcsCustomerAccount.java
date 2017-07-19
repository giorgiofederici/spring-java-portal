package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giorgiofederici.sjp.domain.entity.User;

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ocs_customer_accounts")
public class OcsCustomerAccount implements java.io.Serializable {

	private static final long serialVersionUID = -1277385238472293213L;

	private String firstName;
	private String midName;
	private String lastName;
	private Date birthDate;
	private String address;
	private String email;
	private String mobile;
	private Date startDate;
	private String username;
	private User user;
	private OcsAddress billingAddress;

	public OcsCustomerAccount() {
	}

	public OcsCustomerAccount(String username, String firstName, String lastName, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Id
	@Column(name = "username", nullable = false, unique = true, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "mid_name", nullable = false, length = 45)
	public String getMidName() {
		return this.midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false, length = 10)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "address", nullable = false, length = 255)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", nullable = false, length = 45)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customerAccount")
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// @OneToOne(fetch = FetchType.LAZY, mappedBy = "customerAccount")
	// public OcsAddress getBillingAddress() {
	// return billingAddress;
	// }
	//
	// public void setBillingAddress(OcsAddress billingAddress) {
	// this.billingAddress = billingAddress;
	// }

}
