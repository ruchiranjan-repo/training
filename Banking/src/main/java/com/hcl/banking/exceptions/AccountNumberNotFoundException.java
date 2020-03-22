package com.hcl.banking.exceptions;

public class AccountNumberNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final Long  ERROR_CODE=601L;
	
	public AccountNumberNotFoundException(Long accountNumber)
	{
		super("Account with account number "+ accountNumber+ " does not exists.");
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
	
	

}
