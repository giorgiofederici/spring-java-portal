package com.giorgiofederici.sjp.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;

@Entity
@Table(name = "sjp_users", catalog = "sjp")
@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = -3297129242495403216L;

	private String username;
	private String email;
	private String password;
	private boolean enabled;

	private UserProfileImage userProfileImage;

	private OcsCustomerAccount customerAccount;

	private Set<Authority> authorities = new HashSet<Authority>(0);

	public User() {

	}

	public User(String username, String email, boolean enabled) {
		this.username = username;
		this.email = email;
		this.enabled = enabled;
	}

	public User(String username, String email, String password, boolean enabled) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;

	}

	public User(String username, String email, String password, boolean enabled, Set<Authority> authorities) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "username")
	public OcsCustomerAccount getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(OcsCustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	@OneToOne
	@PrimaryKeyJoinColumn(name = "username")
	@JsonIgnore
	public UserProfileImage getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(UserProfileImage userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", enabled=" + enabled + "]";
	}

}
