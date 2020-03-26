package com.ecommercedemo.ecommerce.service;

import com.ecommercedemo.ecommerce.dto.ProductResponse;
import com.ecommercedemo.ecommerce.dto.ShopResponse;

public interface ProductService {

	ProductResponse getProducts(String productName);

	ShopResponse getShopDetalis(Long productId);

}
