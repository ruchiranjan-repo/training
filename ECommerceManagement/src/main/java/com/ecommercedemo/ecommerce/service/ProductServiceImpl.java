package com.ecommercedemo.ecommerce.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercedemo.ecommerce.dto.ProductResponse;
import com.ecommercedemo.ecommerce.dto.ShopResponse;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.ProductShopDetails;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.exception.ProductNameNotFoundException;
import com.ecommercedemo.ecommerce.exception.ProductNotFoundException;
import com.ecommercedemo.ecommerce.exception.ShopNotFoundException;
import com.ecommercedemo.ecommerce.repository.ProductRepository;
import com.ecommercedemo.ecommerce.repository.ProductShopDetailsRepository;

/**
 * 
 * ProductServiceImpl is used to fetch the product and ship details
 * 
 * @author ShaikYaseen
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductShopDetailsRepository productShopDetailsRepository;

	/**
	 * @throws ProductNotFoundException
	 *  getProducts is used to send product by using  product name
	 */
	public ProductResponse getProducts(String productName) {

		List<Product> products = productRepository.findByProductNameContains(productName);

		if (products.isEmpty()) {
			throw new ProductNameNotFoundException(productName);
		}

		return new ProductResponse(products, products.size());
	}
	/**
	 * @throws ShopNotFoundException
	 *  getProducts is used to send Shop by using  product id
	 */
	public ShopResponse getShopDetalis(Long productId) {

		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new ProductNotFoundException(productId);
		}

		List<ProductShopDetails> productShopDetails = productShopDetailsRepository.findShopDetails(productId);

		if (productShopDetails.isEmpty()) {
			throw new ShopNotFoundException(productId);
		}

		List<Shop> shops = new ArrayList<Shop>();

		for (int index = 0; index < productShopDetails.size(); index++) {
			Shop shopDetails = productShopDetails.get(index).getShop();
			shops.add(shopDetails);
		}
		List<Shop> shopDetails = shops.stream().sorted(Comparator.comparingDouble(Shop::getShopAverageRating))
				.collect(Collectors.toList());

		return new ShopResponse(shopDetails, shopDetails.size());
	}

}
