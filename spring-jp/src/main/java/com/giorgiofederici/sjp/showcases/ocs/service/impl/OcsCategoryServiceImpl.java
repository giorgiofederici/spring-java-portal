package com.giorgiofederici.sjp.showcases.ocs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCategoryDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CategoryForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCategoryService;

@Service
public class OcsCategoryServiceImpl implements OcsCategoryService {

	@Autowired
	private OcsCategoryDao categoryDao;

	@Override
	public void createCategory(CategoryForm categoryForm) {
		OcsCategory category = new OcsCategory(categoryForm.getName());
		this.categoryDao.createCategory(category);
	}

	@Override
	public OcsCategory getCategoryById(Integer categoryId) {
		return this.categoryDao.getCategoryById(categoryId);
	}

	@Override
	public List<OcsCategory> findAll() {
		return this.categoryDao.findAll();
	}

	@Override
	public List<OcsCategory> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		return this.categoryDao.findAll(start, length, orderColumn, orderDir, searchValue);
	}

	@Override
	public int getTotalCategoriesNumber() {
		return this.categoryDao.getTotalCategoriesNumber();
	}

	@Override
	public void updateCategory(CategoryForm categoryForm) {

		OcsCategory category = new OcsCategory();
		category.setId(categoryForm.getId());
		category.setName(categoryForm.getName());

		this.categoryDao.updateCategory(category);

	}

	@Override
	public void deleteCategory(Integer categoryId) {
		this.categoryDao.deleteCategory(categoryId);
	}

}
