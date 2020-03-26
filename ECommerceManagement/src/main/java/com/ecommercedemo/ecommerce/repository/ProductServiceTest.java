package com.ecommercedemo.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecommercedemo.ecommerce.dto.ProductResponse;
import com.ecommercedemo.ecommerce.entity.Category;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.ProductShopDetails;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductService productServiceImpl;

	@MockBean
	private ProductRepository productRepository;

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

		when(productRepository.findByProductNameContains(product.getProductName())).thenReturn(products);

		ProductResponse productResponse = productService.getProducts(product.getProductName());

		ProductResponse productResponseResponse = new ProductResponse(products, products.size());

		assertEquals(productResponseResponse.getProducts(), productResponse.getProducts());

		assertEquals(productResponseResponse.getProductListSize(), productResponse.getProductListSize());

	}

	/*
	 * @Test public void getShopsTest() { List<Shop> shops = new ArrayList<Shop>();
	 * shops.add(shop);
	 * 
	 * when(productRepository.).thenReturn(products);
	 * 
	 * ProductResponse productResponse =
	 * productService.getProducts(product.getProductName());
	 * 
	 * ProductResponse productResponseResponse = new ProductResponse(products,
	 * products.size());
	 * 
	 * assertEquals(productResponseResponse.getProducts(),
	 * productResponse.getProducts());
	 * 
	 * assertEquals(productResponseResponse.getProductListSize(),
	 * productResponse.getProductListSize());
	 * 
	 * }
	 */

}
