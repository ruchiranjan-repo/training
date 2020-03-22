package com.hcl.banking.exceptions;

public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	private static final Long ERROR_CODE = 602L;

	public CustomerNotFoundException(Long customerId) {
		super("Customer with customer Id " + customerId + " does not exists.");
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}
	

}
