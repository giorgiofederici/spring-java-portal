package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ocs_categories")
public class OcsCategory implements java.io.Serializable {

	private static final long serialVersionUID = -6450365038127267555L;

	private Integer id;
	private String name;
	private Set<OcsProduct> catalogs = new HashSet<OcsProduct>(0);

	public OcsCategory() {
	}

	public OcsCategory(String name) {
		this.name = name;
	}

	public OcsCategory(String name, Set<OcsProduct> catalogs) {
		this.name = name;
		this.catalogs = catalogs;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	@JsonIgnore
	public Set<OcsProduct> getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(Set<OcsProduct> catalogs) {
		this.catalogs = catalogs;
	}

}
