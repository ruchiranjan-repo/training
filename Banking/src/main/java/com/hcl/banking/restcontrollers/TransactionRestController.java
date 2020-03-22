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

import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.AccountNumberNotFoundException;
import com.hcl.banking.exceptions.TransactionFailedException;
import com.hcl.banking.exceptions.TransactionNotFoundException;
import com.hcl.banking.services.TransactionService;

@RestController
public class TransactionRestController {
	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/transactions/{transactionreference}", method = RequestMethod.GET)
	public ResponseEntity<Object> getTransactionsByTransactionReference(
			@PathVariable("transactionreference") Long transactionReference) {
		TransactionDTO transactionDto = transactionService.getTransactionByTransactionReference(transactionReference);
		return new ResponseEntity<Object>(transactionDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/transactions/transaction/{accountNumber}", method = RequestMethod.GET)
	public ResponseEntity<Object> getTransactionsByAccountNumber(@PathVariable("accountNumber") Long accountNumber) {
		List<TransactionDTO> transactionDtos = transactionService.getTransactionsByAccountNumber(accountNumber);
		return new ResponseEntity<Object>(transactionDtos, HttpStatus.OK);

	}

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<Object> initiateTransaction(@RequestBody TransactionDTO transaction)
			throws AccountNumberNotFoundException, TransactionFailedException {
		transactionService.initiateTransaction(transaction);
		return new ResponseEntity<>("Transaction Successfull", HttpStatus.OK);
	}

	
}
