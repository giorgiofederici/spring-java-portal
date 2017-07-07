package com.giorgiofederici.sjp.domain.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class TwitterSearchCriteriaForm {

	@NotNull(message = "Search can not be empty")
	@NotEmpty(message = "Search can not be empty")
	private String searchQuery;

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

}
