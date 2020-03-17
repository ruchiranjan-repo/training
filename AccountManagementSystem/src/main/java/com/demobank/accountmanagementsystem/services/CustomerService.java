package com.demobank.accountmanagementsystem.services;

import com.demobank.accountmanagementsystem.dto.CustomerDTO;
import com.demobank.accountmanagementsystem.exceptions.CustomerNotFoundException;

public interface CustomerService {
	public CustomerDTO createCustomer(CustomerDTO customer);
	public CustomerDTO findCustomer(Long customerId) throws CustomerNotFoundException;
	public  CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFoundException;
	public  void deleteCustomer(Long customerId) throws CustomerNotFoundException;

}
