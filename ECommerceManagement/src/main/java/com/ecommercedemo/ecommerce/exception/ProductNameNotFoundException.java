package com.ecommercedemo.ecommerce.exception;

/**
 * Exception to be throw when the Product with provided product name does not
 * exists.
 * 
 * @author YaseenShaik
 *
 */
public class ProductNameNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 601;

	public ProductNameNotFoundException(String productName) {
		super("Product with product name: " + productName + " not found.");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}
}
