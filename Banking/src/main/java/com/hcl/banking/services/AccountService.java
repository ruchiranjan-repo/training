package com.hcl.banking.services;

import java.util.List;

import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.AccountNumberNotFoundException;
import com.hcl.banking.exceptions.TransactionFailedException;

public interface AccountService {
	
	AccountDTO createAccount(AccountDTO account) ;
	
	AccountDTO updateAccount(Long accountNumber, AccountDTO account)  throws AccountNumberNotFoundException;
	
	AccountDTO deleteAccount(Long accountNumber)  throws AccountNumberNotFoundException;
	
	AccountDTO fetchAccountByAccountNumber(Long accountNumber)  throws AccountNumberNotFoundException;
	
	List<AccountDTO> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException;
	
	void initiateTransaction(TransactionDTO transaction) throws TransactionFailedException;
	

}
