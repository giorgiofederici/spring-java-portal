package com.giorgiofederici.sjp.showcases.ocs.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;

public class ShoppingCartDto {

	private List<OcsProduct> products = new ArrayList<OcsProduct>();

	public List<OcsProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OcsProduct> products) {
		this.products = products;
	}

	public void addProduct(OcsProduct product) {
		this.products.add(product);
	}

	public void removeProduct(Integer productId) {

		for (OcsProduct product : this.products) {
			if (product.getId() == productId) {
				this.products.remove(product);
				break;
			}
		}
	}

}
