package com.demobank.accountmanagementsystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demobank.accountmanagementsystem.constants.AccountStatus;
import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.repositories.AccountRepository;
import com.demobank.accountmanagementsystem.util.AccountManagementUtils;

@Service
public class AccountServiceImpl implements AccountService {

	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		account.setAccountNumber(AccountManagementUtils.accountNumberGenerator());		
	    return  accountRepository.save(account);
		
	}

	@Override
	public Account updateAccount(Long accountNumber, Account account) throws AccountNumberNotFoundException {
		Account acc= accountRepository.findByAccountNumber(accountNumber);
		if(acc !=null)
		{
			acc.setAccountStatus(account.getAccountStatus());			
			acc.setAvailableBalance(account.getAvailableBalance());
			
			return accountRepository.save(acc);			
		}
		else
		{
		throw new AccountNumberNotFoundException();
		}
	
	}

	@Override
	public Account deleteAccount(Long accountNumber) throws AccountNumberNotFoundException {
		Account acc= accountRepository.findByAccountNumber(accountNumber);
		if(acc !=null)
		{
			acc.setAccountStatus(AccountStatus.DELETED.toString());
			return accountRepository.save(acc);			
		}
		else
		{
		throw new AccountNumberNotFoundException();
		}
	}

	@Override
	public Account fetchAccountByAccountNumber(Long accountNumber) throws AccountNumberNotFoundException {
		Account acc= accountRepository.findByAccountNumber(accountNumber);
		if(acc == null)
		{
			throw new AccountNumberNotFoundException();
		}
		return acc;
	}

	@Override
	public List<Account> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException	 {
		List<Account> accounts= new ArrayList<Account>();
		accounts= accountRepository.findByIfsc(ifscCode);
		if(CollectionUtils.isEmpty(accounts))
		{
			throw new AccountNumberNotFoundException();
		}
		return accounts;
	}

}
