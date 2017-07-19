package com.giorgiofederici.sjp.showcases.ocs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCategoryDao;
import com.giorgiofederici.sjp.showcases.ocs.dao.OcsProductDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCategory;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.ProductForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsProductService;

@Service
@Transactional
public class OcsProductServiceImpl implements OcsProductService {

	@Autowired
	private OcsProductDao productDao;

	@Autowired
	private OcsCategoryDao categoryDao;

	@Override
	public void createProduct(ProductForm productForm) {
		OcsProduct product = new OcsProduct();

		OcsCategory category = this.categoryDao.getCategoryById(productForm.getCategoryId());

		product.setCategory(category);
		product.setId(productForm.getId());
		product.setName(productForm.getName());
		product.setPrice(productForm.getPrice());
		product.setStock(productForm.getStock());

		this.productDao.createProduct(product);
	}

	@Override
	public OcsProduct getProductById(Integer productId) {
		return this.productDao.getProductById(productId);
	}

	@Override
	public List<OcsProduct> findAll() {
		return this.productDao.findAll();
	}

	@Override
	public List<ProductForm> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {

		List<ProductForm> productFormList = new ArrayList<ProductForm>();

		List<OcsProduct> products = this.productDao.findAll(start, length, orderColumn, orderDir, searchValue);
		for (OcsProduct product : products) {
			ProductForm productForm = new ProductForm();
			productForm.setId(product.getId());
			productForm.setName(product.getName());
			productForm.setCategoryName(product.getCategory().getName());
			productForm.setPrice(product.getPrice());
			productForm.setStock(product.getStock());

			productFormList.add(productForm);
		}

		return productFormList;

	}

	@Override
	public int getTotalProductsNumber() {
		return this.productDao.getTotalProductsNumber();
	}

	@Override
	public void updateProduct(ProductForm productForm) {
		OcsProduct product = new OcsProduct();
		OcsCategory category = this.categoryDao.getCategoryById(productForm.getCategoryId());

		product.setCategory(category);
		product.setId(productForm.getId());
		product.setName(productForm.getName());
		product.setPrice(productForm.getPrice());
		product.setStock(productForm.getStock());

		this.productDao.updateProduct(product);

	}

	@Override
	public void deleteProduct(Integer productId) {
		this.productDao.deleteProduct(productId);
	}

}
