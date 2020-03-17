package com.demobank.accountmanagementsystem.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.accountmanagementsystem.dto.CustomerDTO;
import com.demobank.accountmanagementsystem.exceptions.CustomerNotFoundException;
import com.demobank.accountmanagementsystem.services.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	
	  @RequestMapping(value = "/customers", method = RequestMethod.POST)
	   public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO customer) {	     
		CustomerDTO savedCustomer= customerService.createCustomer(customer);
	     return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	   }
	  
	  @RequestMapping(value = "/customers", method = RequestMethod.GET)
	   public ResponseEntity<Object> findCustomer(@RequestBody CustomerDTO customer) throws CustomerNotFoundException {	     
		 CustomerDTO savedCustomer=customerService.findCustomer(customer.getCustomerId());
	     return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
	   }
	
	  @RequestMapping(value = "/customers", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDTO customer) throws CustomerNotFoundException {	     
		  CustomerDTO updatedCustomer=customerService.updateCustomer(customer);
	     return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	   }
	  
	  @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> deleteCustomer(@RequestBody CustomerDTO customer)  throws CustomerNotFoundException {	     
		 customerService.deleteCustomer(customer.getCustomerId());
	     return new ResponseEntity<>("Customer Deleted Successfully", HttpStatus.NO_CONTENT);
	   }
	  

		@ExceptionHandler(value = CustomerNotFoundException.class)
		   public ResponseEntity<Object> exception(CustomerNotFoundException customerNotFoundException) {
		      return new ResponseEntity<>("Customer with provided customer id not found.", HttpStatus.NOT_FOUND);
		   }

	
}
