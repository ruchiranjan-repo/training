package com.demobank.accountmanagementsystem.services;

import java.util.List;

import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.models.Account;

public interface AccountService {
	
	Account createAccount(Account account) ;
	
	Account updateAccount(Long accountNumber, Account account)  throws AccountNumberNotFoundException;
	
	Account deleteAccount(Long accountNumber)  throws AccountNumberNotFoundException;
	
	Account fetchAccountByAccountNumber(Long accountNumber)  throws AccountNumberNotFoundException;
	
	List<Account> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException;
	

}
