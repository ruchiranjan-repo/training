package com.ecommercedemo.ecommerce.exception;

/**
 * Exception to be throw when the product with provided product Id does not
 * exists.
 * 
 * @author YaseenShaik
 *
 */
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 603;

	public ProductNotFoundException(Long userId) {
		super("product with product Id: " + userId + " not found.");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}