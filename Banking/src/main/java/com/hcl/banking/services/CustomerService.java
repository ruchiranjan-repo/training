package com.hcl.banking.services;

import com.hcl.banking.dto.CustomerDTO;
import com.hcl.banking.exceptions.CustomerNotFoundException;

public interface CustomerService {
	public CustomerDTO createCustomer(CustomerDTO customer);
	public CustomerDTO findCustomer(Long customerId) throws CustomerNotFoundException;
	public  CustomerDTO updateCustomer(Long customerId, CustomerDTO customer) throws CustomerNotFoundException;
	public  void deleteCustomer(Long customerId) throws CustomerNotFoundException;

}
