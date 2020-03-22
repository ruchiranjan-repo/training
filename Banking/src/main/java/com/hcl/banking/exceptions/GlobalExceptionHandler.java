package com.hcl.banking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hcl.banking.dto.ExceptionDTO;
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccountNumberNotFoundException.class)
	public ResponseEntity<Object> accountNumberNotFoundExceptionHandler(
			AccountNumberNotFoundException accountNumberNotFoundException) {
		
		ExceptionDTO exceptionDto= new ExceptionDTO(accountNumberNotFoundException.getMessage(), accountNumberNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	   public ResponseEntity<Object> exception(CustomerNotFoundException customerNotFoundException) {

		ExceptionDTO exceptionDto= new ExceptionDTO(customerNotFoundException.getMessage(), customerNotFoundException.getErrorCode());
	      return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = TransactionFailedException.class)
	public ResponseEntity<Object> transactionFailedExceptionHandler(
			TransactionFailedException transactionFailedException) {
		ExceptionDTO exceptionDto= new ExceptionDTO(transactionFailedException.getMessage(), transactionFailedException.getErrorCode());
		return new ResponseEntity<>(exceptionDto, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value = TransactionNotFoundException.class)
	public ResponseEntity<Object> transactionNotFoundExceptionHandler(
			TransactionNotFoundException transactionNotFoundException) {
		ExceptionDTO exceptionDto= new ExceptionDTO(transactionNotFoundException.getMessage(), transactionNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AccountNotFoundWithIFSCCode.class)
	public ResponseEntity<Object> accountNotFoundWithIFSCCodeHandler(
			AccountNotFoundWithIFSCCode accountNotFoundWithIFSCCode) {
		ExceptionDTO exceptionDto= new ExceptionDTO(accountNotFoundWithIFSCCode.getMessage(), accountNotFoundWithIFSCCode.getErrorCode());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}

}
