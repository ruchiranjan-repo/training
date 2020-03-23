package com.hcl.banking.services;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hcl.banking.constant.TestData;
import com.hcl.banking.constants.AccountStatus;
import com.hcl.banking.constants.AccountType;
import com.hcl.banking.constants.TransactionStatus;
import com.hcl.banking.constants.TransactionType;
import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.dto.AddressDTO;
import com.hcl.banking.dto.CustomerDTO;
import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Address;
import com.hcl.banking.models.Customer;
import com.hcl.banking.models.Transaction;
import com.hcl.banking.repositories.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	
	@MockBean
	CustomerRepository customerRepository;
	

	private Customer customer;
	private CustomerDTO customerDto;
	private Address address;
	private AddressDTO addressDto;
	private Account benificiaryAccount, fromAccount;
	private AccountDTO benificiaryAccountDto;
	private AccountDTO updateAccountRequest;
	List<Account> accounts = new ArrayList<Account>();
	Transaction benificiaryAccountTransactionSuccess, fromAccountTransactionSuccess,
			benificiaryAccountTransactionFailed, fromAccountTransactionFailed;
	TransactionDTO benificiaryAccountTransactionSuccessDto,benificiaryAccountTransactionFailedDto;

	@BeforeEach
	public void setUp() {

		customer = new Customer();
		customer.setCustomerId(TestData.CUSTOMER_ID);
		customer.setCustomerName(TestData.CUSTOMER_NAME);
		customer.setCustomerEmail(TestData.CUSTOMER_EMAIL_ID);
		customer.setCustomerDob(TestData.CUSTOMER_DOB);
		customer.setCustomerContact(TestData.CUSTOMER_CONTACT);
		address = new Address();
		address.setAddressCity(TestData.CUSTOMER_ADDRESS_CITY);
		address.setAddressPin(TestData.CUSTOMER_ADDRESS_PIN);
		address.setAddressStreet(TestData.CUSTOMER_ADDRESS_STREET);
		address.setAddressType(TestData.ADRESS_TYPE);
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		customer.setCustomerAddress(addressList);

		benificiaryAccount = new Account();
		benificiaryAccount.setAccountId(TestData.ACCOUNT_ID_2);
		benificiaryAccount.setAccountNumber(TestData.ACCOUNT_NUMBER);
		benificiaryAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		benificiaryAccount.setAccountType(AccountType.SAVINGS.toString());
		benificiaryAccount.setAvailableBalance(new BigDecimal(10000));
		benificiaryAccount.setIfsc(TestData.IFSC_CODE);
		benificiaryAccount.setCustomer(customer);
		List<Account> accounts= new ArrayList<>();
		accounts.add(benificiaryAccount);
		customer.setAccounts(accounts);
		
		
		customerDto = new CustomerDTO();
		customerDto.setCustomerName(TestData.CUSTOMER_NAME);
		customerDto.setCustomerEmail(TestData.CUSTOMER_EMAIL_ID);
		customerDto.setCustomerDob(TestData.CUSTOMER_DOB);
		customerDto.setCustomerContact(TestData.CUSTOMER_CONTACT);
		addressDto = new AddressDTO();
		addressDto.setAddressCity(TestData.CUSTOMER_ADDRESS_CITY);
		addressDto.setAddressPin(TestData.CUSTOMER_ADDRESS_PIN);
		addressDto.setAddressStreet(TestData.CUSTOMER_ADDRESS_STREET);
		addressDto.setAddressType(TestData.ADRESS_TYPE);
		List<AddressDTO> addressDtoList = new ArrayList<AddressDTO>();
		addressDtoList.add(addressDto);
		customerDto.setCustomerAddress(addressDtoList);

		benificiaryAccountDto = new AccountDTO();
		benificiaryAccountDto.setAccountNumber(TestData.ACCOUNT_NUMBER);
		benificiaryAccountDto.setAccountStatus(AccountStatus.ACTIVE.toString());
		benificiaryAccountDto.setAccountType(AccountType.SAVINGS.toString());
		benificiaryAccountDto.setAvailableBalance(new BigDecimal(10000));
		benificiaryAccountDto.setIfsc(TestData.IFSC_CODE);
		benificiaryAccountDto.setCustomer(customerDto);
		List<AccountDTO> accountDtos= new ArrayList<AccountDTO>();
		accountDtos.add(benificiaryAccountDto);
		customerDto.setAccounts(accountDtos);
		
	}
	
	/*
	 * @Test public void testCreateCustomer() {
	 * 
	 * when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(
	 * customer); CustomerDTO creadtedCustomer=
	 * customerService.createCustomer(customerDto);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}
