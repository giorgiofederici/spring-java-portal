package com.giorgiofederici.sjp.showcases.ocs.domain.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryForm {

	private Integer id;
	private String name;

	public CategoryForm() {

	}

	public CategoryForm(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull(message = "{sjp.admin.ocs.categories.management.addCategory.name.notNull}")
	@NotEmpty(message = "{sjp.admin.ocs.categories.management.addCategory.name.notEmpty}")
	@Length(message = "{sjp.admin.ocs.categories.management.addCategory.name.length}")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
