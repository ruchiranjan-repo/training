package com.ecommercedemo.ecommerce.dto;

import java.util.List;

import com.ecommercedemo.ecommerce.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * ProductResponse is used to  send the product details
 * @author User1
 *
 */

public class ProductResponse {

	@JsonProperty("products")
	private List<Product> products;

	@JsonProperty("productListSize")
	private Integer productListSize;

	public ProductResponse(List<Product> products, Integer productListSize) {
		super();
		this.products = products;
		this.productListSize = productListSize;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getProductListSize() {
		return productListSize;
	}

	public void setProductListSize(Integer productListSize) {
		this.productListSize = productListSize;
	}

}
