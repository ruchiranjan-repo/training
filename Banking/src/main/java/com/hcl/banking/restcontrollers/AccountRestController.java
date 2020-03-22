package com.hcl.banking.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.exceptions.AccountNumberNotFoundException;
import com.hcl.banking.services.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<Object> createAccount(@RequestBody AccountDTO account) {

		AccountDTO savedAccount = accountService.createAccount(account);

		return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/accounts/account/{accountnumber}", method = RequestMethod.GET)
	public ResponseEntity<Object> findAccountByAccountNumber(@PathVariable("accountnumber") Long accountNumber)
			throws AccountNumberNotFoundException {
		AccountDTO account = accountService.fetchAccountByAccountNumber(accountNumber);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{ifsc}", method = RequestMethod.GET)
	public ResponseEntity<Object> findAccountsByIfsc(@PathVariable("ifsc") String ifsc)
			throws AccountNumberNotFoundException {
		List<AccountDTO> accounts = accountService.fetchAccountsByIfscCode(ifsc);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/account/{accountNumber}", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteAccount(@PathVariable("accountNumber") Long accountNumber)
			throws AccountNumberNotFoundException {
		AccountDTO account = accountService.deleteAccount(accountNumber);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{accountNumber}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAccount(@PathVariable("accountNumber") Long accountNumber,
			@RequestBody AccountDTO account) throws AccountNumberNotFoundException {
		AccountDTO updatedAccount = accountService.updateAccount(accountNumber, account);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	

}
