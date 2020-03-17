package com.demobank.accountmanagementsystem.services;

import java.util.List;

import com.demobank.accountmanagementsystem.dto.AccountDTO;
import com.demobank.accountmanagementsystem.dto.TransactionDTO;
import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.exceptions.TransactionFailedException;

public interface AccountService {
	
	AccountDTO createAccount(AccountDTO account) ;
	
	AccountDTO updateAccount(Long accountNumber, AccountDTO account)  throws AccountNumberNotFoundException;
	
	AccountDTO deleteAccount(Long accountNumber)  throws AccountNumberNotFoundException;
	
	AccountDTO fetchAccountByAccountNumber(Long accountNumber)  throws AccountNumberNotFoundException;
	
	List<AccountDTO> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException;
	
	void initiateTransaction(TransactionDTO transaction) throws TransactionFailedException;
	

}
