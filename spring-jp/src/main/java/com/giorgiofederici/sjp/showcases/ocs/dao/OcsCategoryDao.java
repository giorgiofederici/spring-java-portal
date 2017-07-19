package com.giorgiofederici.sjp.showcases.ocs.dao;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;

public interface OcsCategoryDao {

	public void createCategory(OcsCategory category);

	public OcsCategory getCategoryById(Integer categoryId);

	public List<OcsCategory> findAll();

	public List<OcsCategory> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalCategoriesNumber();

	public void updateCategory(OcsCategory category);

	public void deleteCategory(Integer categoryId);

}
