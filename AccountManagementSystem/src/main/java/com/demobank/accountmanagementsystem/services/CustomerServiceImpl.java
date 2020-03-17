package com.demobank.accountmanagementsystem.services;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.accountmanagementsystem.dto.CustomerDTO;
import com.demobank.accountmanagementsystem.exceptions.CustomerNotFoundException;
import com.demobank.accountmanagementsystem.mapper.Mapper;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.models.Customer;
import com.demobank.accountmanagementsystem.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Mapper mapper;
	
	Logger log = Logger.getLogger(CustomerServiceImpl.class);

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDto) {
		Customer customer= mapper.mapCustomerDtoToCustomer(customerDto);
		Customer savedCustomer= customerRepository.save(customer);
		log.info("Customer with customer id"+savedCustomer.getCustomerId()+"saved successfully");
		return mapper.mapCustomerToCustomerDto(savedCustomer);

	}

	@Override
	public CustomerDTO findCustomer(Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null) {
			log.warn("Customer with customer id:"+customerId+"not found.");
			throw new CustomerNotFoundException();
		}
		log.info("Customer with customer id:"+customerId+" found.");
		return mapper.mapCustomerToCustomerDto(customer);
	}

	@Override
	public CustomerDTO updateCustomer( CustomerDTO customerDto) throws CustomerNotFoundException {
		
		Customer cust = customerRepository.findByCustomerId(customerDto.getCustomerId());
		if (cust != null) {
			cust.setCustomerContact(customerDto.getCustomerContact());
			cust.setCustomerEmail(customerDto.getCustomerEmail());
			
			if(!org.springframework.util.CollectionUtils.isEmpty(customerDto.getAccounts()))
					{
				     List<Account> existingAccounts=cust.getAccounts();
				     existingAccounts.addAll(mapper.mapAccountDtosToAccouts(customerDto.getAccounts()));
				     cust.setAccounts(existingAccounts);
					}
			Customer updatedCustomer=customerRepository.save(cust);
			log.info("Customer with customer id"+updatedCustomer.getCustomerId()+"saved successfully");
			
			return mapper.mapCustomerToCustomerDto(updatedCustomer);
		} else {
			log.warn("Customer with customer id:"+customerDto.getCustomerId()+"not found.");
			throw new CustomerNotFoundException();
		}

	}

	@Override
	public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
		Customer cust = customerRepository.findByCustomerId(customerId);
		if (cust != null) {
			customerRepository.delete(cust);
			log.info("Customer with customer id"+customerId+"deleted successfully");
		} else {
			log.warn("Customer with customer id:"+customerId+"not found.");
			throw new CustomerNotFoundException();
		}
	}

}
