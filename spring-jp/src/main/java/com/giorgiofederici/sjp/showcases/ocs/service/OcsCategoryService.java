package com.giorgiofederici.sjp.showcases.ocs.service;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CategoryForm;

public interface OcsCategoryService {

	public void createCategory(CategoryForm categoryForm);

	public OcsCategory getCategoryById(Integer categoryId);

	public List<OcsCategory> findAll();

	public List<OcsCategory> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalCategoriesNumber();

	public void updateCategory(CategoryForm categoryForm);

	public void deleteCategory(Integer categoryId);

}
