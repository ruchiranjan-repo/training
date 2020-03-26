package com.ecommercedemo.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.ecommercedemo.ecommerce.dto.ProductResponse;
import com.ecommercedemo.ecommerce.dto.ShopResponse;
import com.ecommercedemo.ecommerce.entity.Category;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.ProductShopDetails;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.restcontroller.ProductController;
import com.ecommercedemo.ecommerce.service.ProductService;
import com.ecommercedemo.ecommerce.service.ProductServiceImpl;

@SpringBootTest
public class ProductControllerTest {

	@MockBean
	private ProductService productServiceImpl;

	@Autowired
	private ProductController productController;

	Product product, product1;

	Category category, category1;

	ProductShopDetails productShopDetails;
	Shop shop;

	@BeforeEach
	public void setUp() {

		product = new Product();

		product.setProductCost(2000.0);
		product.setProductDescription("good looking");
		product.setProductName("Jeans Shirt");
		product1 = new Product();

		product1.setProductCost(4000.0);
		product1.setProductDescription("good");
		product1.setProductName("Nilon Shirt");

		shop = new Shop();
		shop.setShopAverageRating(4.4);
		shop.setShopDescription("Fash");
		shop.setShopName("Brand fACTORY");
		shop.setShopLocation("Bangalore");

		productShopDetails = new ProductShopDetails();
		productShopDetails.setProduct(product);
		productShopDetails.setShop(shop);
	}

	@Test
	public void getProductsTest() {

		List<Product> products = new ArrayList<Product>();
		products.add(product);

		ProductResponse productResponse = new ProductResponse(products, products.size());

		Mockito.when(productServiceImpl.getProducts(product.getProductName())).thenReturn(productResponse);
		
		ResponseEntity<ProductResponse> responseEntity = productController.getProducts(product.getProductName());

		assertEquals(productResponse.getProductListSize(), responseEntity.getBody().getProductListSize());

		assertEquals(productResponse.getProducts(), responseEntity.getBody().getProducts());

	}

	@Test
	public void getShopDetalisTest() {

		List<Shop> shopDetails = new ArrayList<Shop>();
		shopDetails.add(shop);

		ShopResponse shopResponse = new ShopResponse(shopDetails, shopDetails.size());

		Mockito.when(productServiceImpl.getShopDetalis(1l)).thenReturn(shopResponse);
		ResponseEntity<ShopResponse> responseEntity = productController.getShopDetalis(1l);

		assertEquals(shopResponse.getShops(), responseEntity.getBody().getShops());

		assertEquals(shopResponse.getShopsListSize(), responseEntity.getBody().getShopsListSize());

	}

}
