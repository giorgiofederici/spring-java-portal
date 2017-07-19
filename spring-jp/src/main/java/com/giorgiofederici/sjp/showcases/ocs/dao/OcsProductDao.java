package com.giorgiofederici.sjp.showcases.ocs.dao;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;

public interface OcsProductDao {

	public void createProduct(OcsProduct product);

	public OcsProduct getProductById(Integer productId);

	public List<OcsProduct> findAll();

	public List<OcsProduct> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalProductsNumber();

	public void updateProduct(OcsProduct product);

	public void deleteProduct(Integer productId);

}
