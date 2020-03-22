package com.hcl.banking.exceptions;

public class TransactionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 604L;

	public TransactionNotFoundException(Long reference) {
		super("No transaction found for  "+reference);
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}
}
