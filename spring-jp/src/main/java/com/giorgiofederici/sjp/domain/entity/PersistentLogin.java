package com.giorgiofederici.sjp.domain.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persistent_logins", catalog = "sjp")
public class PersistentLogin implements Serializable {

	private static final long serialVersionUID = 8184253495568132227L;

	private String series;
	private String username;
	private String token;
	private Calendar lastUsed;

	public PersistentLogin() {

	}

	public PersistentLogin(String series, String username, String token, Calendar lastUsed) {
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	@Id
	@Column(name = "series", unique = true, nullable = false, length = 64)
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "username", nullable = false, length = 64)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "token", nullable = false, length = 64)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used", nullable = false)
	public Calendar getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Calendar lastUsed) {
		this.lastUsed = lastUsed;
	}

}