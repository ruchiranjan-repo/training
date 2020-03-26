package com.ecommercedemo.ecommerce.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercedemo.ecommerce.dto.ProductResponse;
import com.ecommercedemo.ecommerce.dto.ShopResponse;
import com.ecommercedemo.ecommerce.exception.ProductNameNotFoundException;
import com.ecommercedemo.ecommerce.exception.ShopNotFoundException;
import com.ecommercedemo.ecommerce.service.ProductService;

/**
 * ProductController is used to take product request from the client and sending
 * response
 * 
 * @author YaseenShaik
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * 
	 * @throws ProductNameNotFoundException
	 * @return All products details
	 */
	@GetMapping("")
	public ResponseEntity<ProductResponse> getProducts(@RequestParam("productName") String productName) {

		logger.info("Fetching product details");

		return new ResponseEntity<>(productService.getProducts(productName), HttpStatus.OK);
	}

	/**
	 * @return Shop details
	 * @throws ShopNotFoundException
	 * 
	 */
	@GetMapping("/{productId}")
	public ResponseEntity<ShopResponse> getShopDetalis(@PathVariable Long productId) {

		logger.info("Fetching product details");

		return new ResponseEntity<>(productService.getShopDetalis(productId), HttpStatus.OK);
	}
}
