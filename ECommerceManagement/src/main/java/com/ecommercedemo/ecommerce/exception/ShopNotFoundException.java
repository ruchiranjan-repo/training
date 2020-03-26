package com.ecommercedemo.ecommerce.exception;
/**
 * Exception to be throw when the shop with provided shop Id does not exists.
 * @author YaseenShaik
 *
 */
public class ShopNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 601;

	public ShopNotFoundException(Long shopId) {
		super("Shop with shop Id: " + shopId +" not found.");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}
	

}
