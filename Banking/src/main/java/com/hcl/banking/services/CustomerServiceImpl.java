package com.hcl.banking.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.dto.CustomerDTO;
import com.hcl.banking.exceptions.CustomerNotFoundException;
import com.hcl.banking.mapper.Mapper;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Customer;
import com.hcl.banking.repositories.CustomerRepository;

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
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		if (!customer.isPresent()) {
			log.warn("Customer with customer id:"+customerId+"not found.");
			throw new CustomerNotFoundException(customerId);
		}
		log.info("Customer with customer id:"+customerId+" found.");
		return mapper.mapCustomerToCustomerDto(customer.get());
	}

	@Override
	public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDto) throws CustomerNotFoundException {
		
		Optional<Customer> cust = customerRepository.findByCustomerId(customerId);
		if (cust.isPresent()) {
			cust.get().setCustomerContact(customerDto.getCustomerContact());
			cust.get().setCustomerEmail(customerDto.getCustomerEmail());
			
			if(!org.springframework.util.CollectionUtils.isEmpty(customerDto.getAccounts()))
					{
				     List<Account> existingAccounts=cust.get().getAccounts();
				     existingAccounts.addAll(mapper.mapAccountDtosToAccouts(customerDto.getAccounts()));
				     cust.get().setAccounts(existingAccounts);
					}
			Customer updatedCustomer=customerRepository.save(cust.get());
			log.info("Customer with customer id"+updatedCustomer.getCustomerId()+"saved successfully");
			
			return mapper.mapCustomerToCustomerDto(updatedCustomer);
		} else {
			log.warn("Customer with customer id:"+customerDto.getCustomerId()+"not found.");
			throw new CustomerNotFoundException(customerId);
		}

	}

	@Override
	public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
		Optional<Customer> cust = customerRepository.findByCustomerId(customerId);
		if (cust != null) {
			customerRepository.delete(cust.get());
			log.info("Customer with customer id"+customerId+"deleted successfully");
		} else {
			log.warn("Customer with customer id:"+customerId+"not found.");
			throw new CustomerNotFoundException(customerId);
		}
	}

}
