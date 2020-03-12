package com.demobank.accountmanagementsystem.restcontrollers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.services.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/accounts", method=RequestMethod.POST)
	public ResponseEntity<Object> createAccount(@RequestBody Account account)
	{
		Account savedAccount=accountService.createAccount(account);
		
		return new ResponseEntity<>(savedAccount,HttpStatus.CREATED);		
	}
	
	 @RequestMapping(value = "/accounts/account/{accountnumber}", method = RequestMethod.GET)
	   public ResponseEntity<Object> findAccountByAccountNumber(@PathVariable("accountnumber") Long accountNumber) throws AccountNumberNotFoundException  {	     
		Account account= accountService.fetchAccountByAccountNumber(accountNumber);
	     return new ResponseEntity<>(account, HttpStatus.OK);
	   }
	 
	 
	 @RequestMapping(value = "/accounts/{ifsc}", method = RequestMethod.GET)
	   public ResponseEntity<Object> findAccountsByIfsc(@PathVariable("ifsc") String ifsc) throws AccountNumberNotFoundException  {	     
		List<Account> accounts= accountService.fetchAccountsByIfscCode(ifsc);
	     return new ResponseEntity<>(accounts, HttpStatus.OK);
	   }
	 
	 @RequestMapping(value="/accounts/account/{accountNumber}", method=RequestMethod.DELETE)
	 public ResponseEntity<Object> deleteAccount(@PathVariable("accountNumber") Long accountNumber) throws AccountNumberNotFoundException
	 {
		 Account account=accountService.deleteAccount(accountNumber);
			return new ResponseEntity<>(account,HttpStatus.OK);	
	 }
	 
	 @RequestMapping(value="/accounts/{accountNumber}", method=RequestMethod.PUT)
	 public ResponseEntity<Object> updateAccount(@PathVariable("accountNumber") Long accountNumber, @RequestBody Account account) throws AccountNumberNotFoundException
	 {
		 Account updatedAccount=accountService.updateAccount(accountNumber, account);
			return new ResponseEntity<>(updatedAccount,HttpStatus.OK);	
	 }
	 
	 //Exception Handler
	 
	 @ExceptionHandler(value = AccountNumberNotFoundException.class)
	   public ResponseEntity<Object> accountNumberNotFoundExceptionHandler(AccountNumberNotFoundException accountNumberNotFoundException) {
	      return new ResponseEntity<>("Account with provided account number not found.", HttpStatus.NOT_FOUND);
	   }

	
	

}
