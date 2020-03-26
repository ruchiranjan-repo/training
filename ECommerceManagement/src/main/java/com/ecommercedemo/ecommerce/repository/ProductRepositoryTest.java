package com.ecommercedemo.ecommerce.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.ecommercedemo.ecommerce.entity.Category;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.ProductShopDetails;
import com.ecommercedemo.ecommerce.entity.Shop;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductShopDetailsRepository detailsRepository;

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
	public void shopNotFoundExceptionTest() {

		testEntityManager.persistAndFlush(product);

		testEntityManager.persistAndFlush(shop);
		testEntityManager.persistAndFlush(productShopDetails);

		List<ProductShopDetails> productShopDetails = detailsRepository.findShopDetails(20l);

		assertFalse(CollectionUtils.isEmpty(productShopDetails));

	}

	@Test
	public void productNameNotFoundExceptionTest() {

		testEntityManager.persistAndFlush(product);
		testEntityManager.persistAndFlush(product1);

		List<Product> products = productRepository.findByProductNameContains(product.getProductName());

		assertFalse(CollectionUtils.isEmpty(products));

	}

	@Test
	public void productNotFoundExceptionTest() {

		testEntityManager.persistAndFlush(product);
		testEntityManager.persistAndFlush(product1);

		Optional<Product> checkProduct = productRepository.findById(20l);

		assertFalse(checkProduct.isPresent());

	}

}
