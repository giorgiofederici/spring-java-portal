package com.giorgiofederici.sjp.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groups", catalog = "sjp")
public class Group implements Serializable {

	private static final long serialVersionUID = -6747636371090274659L;

	private Long id;
	private String groupName;
	private Set<GroupAuthority> groupAuthorities = new HashSet<GroupAuthority>(0);
	private Set<GroupMember> groupMembers = new HashSet<GroupMember>(0);

	public Group() {

	}

	public Group(Long id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	public Group(Long id, String groupName, Set<GroupAuthority> groupAuthorities, Set<GroupMember> groupMembers) {
		this.id = id;
		this.groupName = groupName;
		this.groupAuthorities = groupAuthorities;
		this.groupMembers = groupMembers;
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

	@Column(name = "group_name", nullable = false, length = 50)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupAuthority> getGroupAuthorities() {
		return groupAuthorities;
	}

	public void setGroupAuthorities(Set<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

}
