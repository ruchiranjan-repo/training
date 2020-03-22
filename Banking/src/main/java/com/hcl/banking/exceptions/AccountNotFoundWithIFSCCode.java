package com.hcl.banking.exceptions;

public class AccountNotFoundWithIFSCCode extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final Long  ERROR_CODE=605L;
	
	public AccountNotFoundWithIFSCCode(String  ifscCode)
	{
		super("Account with ifsc code "+ ifscCode+ " does not exists.");
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
	
}
