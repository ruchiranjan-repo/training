package com.hcl.banking.services;

import java.util.List;

import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.TransactionFailedException;

public interface TransactionService {

	public List<TransactionDTO> getTransactionsByAccountNumber(Long accountNumber);
	
	public TransactionDTO getTransactionByTransactionReference(Long transactionReference);
	
	void initiateTransaction(TransactionDTO transaction) throws TransactionFailedException;
	
}
