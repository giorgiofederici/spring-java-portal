package com.giorgiofederici.sjp.showcases.ocs.service;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.ProductForm;

public interface OcsProductService {

	public void createProduct(ProductForm productForm);

	public OcsProduct getProductById(Integer productId);

	public List<OcsProduct> findAll();

	public List<ProductForm> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalProductsNumber();

	public void updateProduct(ProductForm productForm);

	public void deleteProduct(Integer productId);

}
