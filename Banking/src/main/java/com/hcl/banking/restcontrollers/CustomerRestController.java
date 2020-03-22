package com.hcl.banking.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.dto.CustomerDTO;
import com.hcl.banking.exceptions.CustomerNotFoundException;
import com.hcl.banking.services.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	
	  @RequestMapping(value = "/customers", method = RequestMethod.POST)
	   public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO customer) {	     
		CustomerDTO savedCustomer= customerService.createCustomer(customer);
	     return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	   }
	  
	  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	   public ResponseEntity<Object> findCustomer(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException {	     
		 CustomerDTO savedCustomer=customerService.findCustomer(customerId);
	     return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
	   }
	
	  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") Long customerId,@RequestBody CustomerDTO customer) throws CustomerNotFoundException {	     
		  CustomerDTO updatedCustomer=customerService.updateCustomer(customerId,customer);
	     return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	   }
	  
	  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") Long customerId)  throws CustomerNotFoundException {	     
		 customerService.deleteCustomer(customerId);
	     return new ResponseEntity<>("Customer Deleted Successfully", HttpStatus.NO_CONTENT);
	   }
	  

		
	
}
