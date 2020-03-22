package com.hcl.banking.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.hcl.banking.constant.TestData;
import com.hcl.banking.constants.AccountStatus;
import com.hcl.banking.constants.AccountType;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Address;
import com.hcl.banking.models.Customer;

@DataJpaTest
public class AccountRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	AccountRepository accountRepository;
	
	private Customer customer;
	private Address address;
	private Account account;
	
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
		
		account= new Account();
		account.setAccountNumber(TestData.ACCOUNT_NUMBER);
		account.setAccountStatus(AccountStatus.ACTIVE.toString());
		account.setAccountType(AccountType.SAVINGS.toString());
		account.setAvailableBalance(new BigDecimal(10000));
		account.setIfsc(TestData.IFSC_CODE);
		account.setCustomer(customer);
	}

	
	
	@Test
	public void testFindByAccountId()
	{
		Account persistedAccount=testEntityManager.persist(account);
		
		Optional<Account> account= accountRepository.findByAccountId(persistedAccount.getAccountId());
		
		
		assertThat(persistedAccount.getAccountId()).isEqualTo(account.get().getAccountId());
		assertThat(persistedAccount.getAccountNumber()).isEqualTo(account.get().getAccountNumber());
		assertThat(persistedAccount.getAccountStatus()).isEqualTo(account.get().getAccountStatus());
		
	}
	@Test
	public void testFindByAccountIdNotFound()
	{
		testEntityManager.persist(account);
		
		Optional<Account> account= accountRepository.findByAccountId(10L);
		
		
		assertFalse(account.isPresent());
		
		
	}
	@Test
	public void testFindByAccountNumber()
	{
		Account persistedAccount=testEntityManager.persist(account);
		
		Optional<Account> account= accountRepository.findByAccountNumber(persistedAccount.getAccountNumber());
		
		
		assertThat(persistedAccount.getAccountId()).isEqualTo(account.get().getAccountId());
		assertThat(persistedAccount.getAccountNumber()).isEqualTo(account.get().getAccountNumber());
		assertThat(persistedAccount.getAccountStatus()).isEqualTo(account.get().getAccountStatus());
		
	}
	@Test
	public void testFindByAccountNumberNotFount()
	{
		testEntityManager.persist(account);
		
		Optional<Account> account= accountRepository.findByAccountNumber(10L);
		
		
		assertFalse(account.isPresent());
		
		
	}
	
	@Test
	public void testFindByfindByIfsc()
	{
		Account persistedAccount=testEntityManager.persist(account);
		
		List<Account> account= accountRepository.findByIfsc(persistedAccount.getIfsc());
		
		assertFalse(CollectionUtils.isEmpty(account));
		
		assertThat(1).isEqualTo(account.size());
		
	}
	@Test
	public void testFindByfindByIfscNotFound()
	{
		testEntityManager.persist(account);
		
		List<Account> account= accountRepository.findByIfsc(TestData.IFSC_CODE_NOT_FOUND);
		
		
		assertTrue(CollectionUtils.isEmpty(account));
		
		
	}
	
}
