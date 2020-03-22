package com.hcl.banking.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import  com.hcl.banking.constant.TestData;
import com.hcl.banking.models.Address;
import com.hcl.banking.models.Customer;

@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	
	private Customer customer;
	private Address address;
	
	@BeforeEach
	public void setup()
	{
		 customer= new Customer();
		customer.setCustomerName(TestData.CUSTOMER_NAME);
		customer.setCustomerEmail(TestData.CUSTOMER_EMAIL_ID);
		customer.setCustomerDob(TestData.CUSTOMER_DOB);
		customer.setCustomerContact(TestData.CUSTOMER_CONTACT);
		address= new Address();
		address.setAddressCity(TestData.CUSTOMER_ADDRESS_CITY);
		address.setAddressPin(TestData.CUSTOMER_ADDRESS_PIN);
		address.setAddressStreet(TestData.CUSTOMER_ADDRESS_STREET);
		address.setAddressType(TestData.ADRESS_TYPE);
		List<Address> addressList= new ArrayList<Address>();
		addressList.add(address);
		customer.setCustomerAddress(addressList );
	}
	
	
	@Test
	public void testFindByCustomerId()
	{
		Customer persistedCustomer=testEntityManager.persist(customer);
		
		Optional<Customer> customer= customerRepository.findByCustomerId(persistedCustomer.getCustomerId());
		
		
		assertThat(persistedCustomer.getCustomerId()).isEqualTo(customer.get().getCustomerId());
		assertThat(persistedCustomer.getCustomerName()).isEqualTo(customer.get().getCustomerName());
		assertThat(persistedCustomer.getCustomerDob()).isEqualTo(customer.get().getCustomerDob());
		assertThat(persistedCustomer.getCustomerContact()).isEqualTo(customer.get().getCustomerContact());
		assertThat(persistedCustomer.getCustomerAddress().size()).isEqualTo(customer.get().getCustomerAddress().size());
		
	}
	@Test
	public void testFindByCustomerIdNotFound()
	{
		testEntityManager.persist(customer);
		
		Optional<Customer> customer= customerRepository.findByCustomerId(100L);
		
		
		assertFalse(customer.isPresent());
		
		
	}


}
