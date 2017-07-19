package com.giorgiofederici.sjp.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sjp_authorities", catalog = "sjp", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username", "authority" }) })
public class Authority implements Serializable {

	private static final long serialVersionUID = 7767644793553582144L;

	private Long id;
	private User user;
	private String authority;

	public Authority() {

	}

	public Authority(User user, String authority) {
		this.user = user;
		this.authority = authority;
	}

	public Authority(Long id, User user, String authority) {
		this.id = id;
		this.user = user;
		this.authority = authority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", unique = true, nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "authority", nullable = false, length = 50)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
