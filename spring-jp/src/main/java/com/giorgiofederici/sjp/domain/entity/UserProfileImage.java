package com.giorgiofederici.sjp.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sjp_user_profile_images", catalog = "sjp")
public class UserProfileImage implements Serializable {

	private static final long serialVersionUID = -4757383028833640067L;

	@Id
	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String username;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "type", length = 100, nullable = false)
	private String type;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", nullable = false)
	private byte[] content;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfileImage")
	@JoinColumn(name = "username")
	private User user;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
