package com.hcl.banking.exceptions;

public class TransactionFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 603L;

	public TransactionFailedException(Long fromAccount) {
		super("Transaction failed due to insufficient account balance in "+fromAccount);
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}
}
