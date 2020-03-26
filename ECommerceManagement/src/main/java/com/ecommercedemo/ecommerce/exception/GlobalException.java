package com.ecommercedemo.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommercedemo.ecommerce.dto.ExceptionMessageDTO;
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(ProductNameNotFoundException.class)
	public ResponseEntity<Object> productNameNotFoundException(
			ProductNameNotFoundException productNameNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(productNameNotFoundException.getMessage(),
				productNameNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> productNotFoundException(ProductNotFoundException productNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(productNotFoundException.getMessage(),
				productNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ShopNotFoundException.class)
	public ResponseEntity<Object> shopNotFoundException(ShopNotFoundException shopNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(shopNotFoundException.getMessage(),
				shopNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

}
