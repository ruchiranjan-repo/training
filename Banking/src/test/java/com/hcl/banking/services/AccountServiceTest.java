package com.hcl.banking.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import com.hcl.banking.constant.TestData;
import com.hcl.banking.constants.AccountStatus;
import com.hcl.banking.constants.AccountType;
import com.hcl.banking.constants.TransactionStatus;
import com.hcl.banking.constants.TransactionType;
import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.dto.AddressDTO;
import com.hcl.banking.dto.CustomerDTO;
import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.AccountNotFoundWithIFSCCode;
import com.hcl.banking.exceptions.AccountNumberNotFoundException;
import com.hcl.banking.exceptions.TransactionFailedException;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Address;
import com.hcl.banking.models.Customer;
import com.hcl.banking.models.Transaction;
import com.hcl.banking.repositories.AccountRepository;

@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@MockBean
	AccountRepository accountRepository;

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
//Creating Entity
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
		benificiaryAccountTransactionSuccess = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TransactionStatus.SUCCESS.toString(), benificiaryAccount);
		benificiaryAccountTransactionSuccess.setTransactionReference(TestData.TRANSACTION_REF_1);

		benificiaryAccountTransactionFailed = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TransactionStatus.FAILED.toString(), benificiaryAccount);

		fromAccount = new Account();
		fromAccount.setAccountId(TestData.ACCOUNT_ID_3);
		fromAccount.setAccountNumber(TestData.ACCOUNT_NUMBER_1);
		fromAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		fromAccount.setAccountType(AccountType.SAVINGS.toString());
		fromAccount.setAvailableBalance(new BigDecimal(10000));
		fromAccount.setIfsc(TestData.IFSC_CODE);
		fromAccount.setCustomer(customer);

		fromAccountTransactionSuccess = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.DEBIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TransactionStatus.SUCCESS.toString(), fromAccount);
		fromAccountTransactionSuccess.setTransactionReference(TestData.TRANSACTION_REF_3);

		fromAccountTransactionFailed = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.DEBIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TransactionStatus.FAILED.toString(), fromAccount);
		fromAccountTransactionFailed.setTransactionReference(TestData.TRANSACTION_REF_4);

		// Creating DTO

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

		benificiaryAccountTransactionSuccessDto = new TransactionDTO(null, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TestData.ACCOUNT_NUMBER_1, TransactionStatus.SUCCESS.toString());

	 benificiaryAccountTransactionFailedDto = new TransactionDTO(null, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED,
				TestData.ACCOUNT_NUMBER_1, TransactionStatus.FAILED.toString());

		List<TransactionDTO> transactionDtos = new ArrayList<TransactionDTO>();
		transactionDtos.add(benificiaryAccountTransactionSuccessDto);
		transactionDtos.add(benificiaryAccountTransactionFailedDto);
		benificiaryAccountDto.setTransactionDto(transactionDtos);

		/*
		 * fromAccountDto = new AccountDTO();
		 * fromAccountDto.setAccountNumber(TestData.ACCOUNT_NUMBER_1);
		 * fromAccountDto.setAccountStatus(AccountStatus.ACTIVE.toString());
		 * fromAccountDto.setAccountType(AccountType.SAVINGS.toString());
		 * fromAccountDto.setAvailableBalance(new BigDecimal(10000));
		 * fromAccountDto.setIfsc(TestData.IFSC_CODE);
		 * fromAccountDto.setCustomer(customerDto); fromAccountTransactionSuccessDto =
		 * new TransactionDTO(null, TestData.ACCOUNT_NUMBER,
		 * TransactionType.DEBIT.toString(), LocalDateTime.now(),
		 * TestData.AMOUNT_TRANSAFERED, TestData.ACCOUNT_NUMBER_1,
		 * TransactionStatus.SUCCESS.toString());
		 * 
		 * TransactionDTO fromAccountTransactionFailedDto = new TransactionDTO(null,
		 * TestData.ACCOUNT_NUMBER, TransactionType.DEBIT.toString(),
		 * LocalDateTime.now(), TestData.AMOUNT_TRANSAFERED, TestData.ACCOUNT_NUMBER_1,
		 * TransactionStatus.FAILED.toString());
		 * 
		 * 
		 * List<TransactionDTO> fromTransactionList = new ArrayList<TransactionDTO>();
		 * fromTransactionList.add(fromAccountTransactionSuccessDto);
		 * fromTransactionList.add(fromAccountTransactionFailedDto);
		 * fromAccountDto.setTransactionDto(fromTransactionList);
		 */

		accounts.add(benificiaryAccount);
		accounts.add(fromAccount);

	}

	@Test
	public void testCreateAccount() {

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		benificiaryAccount.setTransaction(transactions);

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(benificiaryAccount);

		AccountDTO accountDto = accountService.createAccount(benificiaryAccountDto);

		assertThat(accountDto.getAccountId()).isEqualTo(benificiaryAccount.getAccountId());
		assertThat(accountDto.getAccountNumber()).isEqualTo(benificiaryAccount.getAccountNumber());
		assertThat(accountDto.getAccountStatus()).isEqualTo(benificiaryAccount.getAccountStatus());
		assertThat(accountDto.getCustomer().getCustomerId())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerId());
		assertThat(accountDto.getCustomer().getCustomerContact())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerContact());
		assertThat(accountDto.getCustomer().getCustomerName())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerName());
		assertThat(accountDto.getTransactionDto().size()).isEqualTo(benificiaryAccount.getTransaction().size());

	}

	@Test
	public void testUpdatedAccount() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		benificiaryAccount.setTransaction(transactions);

		updateAccountRequest = new AccountDTO();
		updateAccountRequest.setAccountStatus("INACTIVE");
		Account UpdatedBenificiaryAccount = benificiaryAccount;
		UpdatedBenificiaryAccount.setAccountStatus("INACTIVE");

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(UpdatedBenificiaryAccount);
		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER))
				.thenReturn(Optional.of(benificiaryAccount));

		AccountDTO accountDto = accountService.updateAccount(TestData.ACCOUNT_NUMBER, updateAccountRequest);

		assertThat(accountDto.getAccountId()).isEqualTo(UpdatedBenificiaryAccount.getAccountId());
		assertThat(accountDto.getAccountNumber()).isEqualTo(UpdatedBenificiaryAccount.getAccountNumber());
		assertThat(accountDto.getAccountStatus()).isEqualTo(UpdatedBenificiaryAccount.getAccountStatus());
		assertThat(accountDto.getCustomer().getCustomerId())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerId());
		assertThat(accountDto.getCustomer().getCustomerContact())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerContact());
		assertThat(accountDto.getCustomer().getCustomerName())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerName());
		assertThat(accountDto.getTransactionDto().size()).isEqualTo(UpdatedBenificiaryAccount.getTransaction().size());

	}

	@Test
	public void testUpdatedAccountThrowsAccountNotFoundException() {
		updateAccountRequest = new AccountDTO();
		updateAccountRequest.setAccountStatus("INACTIVE");

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER)).thenReturn(Optional.empty());

		assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.updateAccount(TestData.ACCOUNT_NUMBER, updateAccountRequest);
		});
	}

	@Test
	public void testDeleteAccount() {

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		benificiaryAccount.setTransaction(transactions);

		Account UpdatedBenificiaryAccount = benificiaryAccount;
		UpdatedBenificiaryAccount.setAccountStatus("DELETED");

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(UpdatedBenificiaryAccount);
		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER))
				.thenReturn(Optional.of(benificiaryAccount));

		AccountDTO accountDto = accountService.deleteAccount(TestData.ACCOUNT_NUMBER);

		assertThat(accountDto.getAccountId()).isEqualTo(UpdatedBenificiaryAccount.getAccountId());
		assertThat(accountDto.getAccountNumber()).isEqualTo(UpdatedBenificiaryAccount.getAccountNumber());
		assertThat(accountDto.getAccountStatus()).isEqualTo(UpdatedBenificiaryAccount.getAccountStatus());
		assertThat(accountDto.getCustomer().getCustomerId())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerId());
		assertThat(accountDto.getCustomer().getCustomerContact())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerContact());
		assertThat(accountDto.getCustomer().getCustomerName())
				.isEqualTo(UpdatedBenificiaryAccount.getCustomer().getCustomerName());
		assertThat(accountDto.getTransactionDto().size()).isEqualTo(UpdatedBenificiaryAccount.getTransaction().size());

	}

	@Test
	public void testDeleteAccountThrowsAccountNotFoundException() {

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER)).thenReturn(Optional.empty());

		assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.deleteAccount(TestData.ACCOUNT_NUMBER);
		});
	}

	@Test
	public void testFetchAccountByAccountNumber() {

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		benificiaryAccount.setTransaction(transactions);

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER))
				.thenReturn(Optional.of(benificiaryAccount));

		AccountDTO accountDto = accountService.fetchAccountByAccountNumber(TestData.ACCOUNT_NUMBER);

		assertThat(accountDto.getAccountId()).isEqualTo(benificiaryAccount.getAccountId());
		assertThat(accountDto.getAccountNumber()).isEqualTo(benificiaryAccount.getAccountNumber());
		assertThat(accountDto.getAccountStatus()).isEqualTo(benificiaryAccount.getAccountStatus());
		assertThat(accountDto.getCustomer().getCustomerId())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerId());
		assertThat(accountDto.getCustomer().getCustomerContact())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerContact());
		assertThat(accountDto.getCustomer().getCustomerName())
				.isEqualTo(benificiaryAccount.getCustomer().getCustomerName());
		assertThat(accountDto.getTransactionDto().size()).isEqualTo(benificiaryAccount.getTransaction().size());

	}

	@Test
	public void testFetchAccountByAccountNumberThrowsAccountNotFoundException() {

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER)).thenReturn(Optional.empty());

		assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.fetchAccountByAccountNumber(TestData.ACCOUNT_NUMBER);
		});
	}

	@Test
	public void testFetchAccountByIFSCCode() {

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		benificiaryAccount.setTransaction(transactions);

		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(fromAccountTransactionSuccess);
		fromAccount.setTransaction(transactions);

		accounts.add(benificiaryAccount);
		accounts.add(fromAccount);

		when(accountRepository.findByIfsc(TestData.IFSC_CODE)).thenReturn(accounts);

		List<AccountDTO> accountDtos = accountService.fetchAccountsByIfscCode(TestData.IFSC_CODE);

		assertFalse(CollectionUtils.isEmpty(accountDtos));

		assertThat(accountDtos.size()).isEqualTo(accounts.size());

	}

	@Test
	public void testFetchAccountByIFSCCodeThrowsAccountNotFoundWithIFSCCode() {

		when(accountRepository.findByIfsc(TestData.IFSC_CODE)).thenReturn(new ArrayList<Account>());

		assertThrows(AccountNotFoundWithIFSCCode.class, () -> {
			accountService.fetchAccountsByIfscCode(TestData.IFSC_CODE);
		});
	}

	@Test
	public void testInitiateTransaction() throws TransactionFailedException {
		Account savedBenificiaryAccount = benificiaryAccount;
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		savedBenificiaryAccount.setTransaction(transactions);

		Account savedFromAccount = fromAccount;
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(fromAccountTransactionSuccess);
		savedFromAccount.setTransaction(transactions);

		TransactionDTO transactionDto = new TransactionDTO();
		transactionDto.setAmount(new BigDecimal(100));
		transactionDto.setBenificiaryAccount(TestData.ACCOUNT_NUMBER);
		transactionDto.setFromAccount(TestData.ACCOUNT_NUMBER_1);

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER))
				.thenReturn(Optional.of(benificiaryAccount));
		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER_1)).thenReturn(Optional.of(fromAccount));

		when(accountRepository.saveAndFlush((Mockito.any(Account.class)))).thenReturn(savedBenificiaryAccount);

		when(accountRepository.saveAndFlush((Mockito.any(Account.class)))).thenReturn(savedFromAccount);

		accountService.initiateTransaction(transactionDto);

	}

	@Test
	public void testInitiateTransactionInSufficientFundException() throws TransactionFailedException {
		Account savedBenificiaryAccount = benificiaryAccount;
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionFailed);
		savedBenificiaryAccount.setTransaction(transactions);

		Account savedFromAccount = fromAccount;
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(fromAccountTransactionFailed);
		savedFromAccount.setTransaction(transactions);

		TransactionDTO transactionDto = new TransactionDTO();
		transactionDto.setAmount(new BigDecimal(1000000));
		transactionDto.setBenificiaryAccount(TestData.ACCOUNT_NUMBER);
		transactionDto.setFromAccount(TestData.ACCOUNT_NUMBER_1);

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER))
				.thenReturn(Optional.of(benificiaryAccount));
		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER_1)).thenReturn(Optional.of(fromAccount));

		when(accountRepository.saveAndFlush((Mockito.any(Account.class)))).thenReturn(savedBenificiaryAccount);

		when(accountRepository.saveAndFlush((Mockito.any(Account.class)))).thenReturn(savedFromAccount);

		assertThrows(TransactionFailedException.class, () -> accountService.initiateTransaction(transactionDto));

	}

	@Test
	public void testInitiateTransactionFromAccountNotFoundException() throws TransactionFailedException {

		TransactionDTO transactionDto = new TransactionDTO();
		transactionDto.setAmount(new BigDecimal(1000000));
		transactionDto.setBenificiaryAccount(TestData.ACCOUNT_NUMBER);
		transactionDto.setFromAccount(TestData.ACCOUNT_NUMBER_1);

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER_1)).thenReturn(Optional.empty());

		assertThrows(AccountNumberNotFoundException.class, () -> accountService.initiateTransaction(transactionDto));

	}

	@Test
	public void testInitiateTransactionBenificiaryAccountNotFoundException() throws TransactionFailedException {

		TransactionDTO transactionDto = new TransactionDTO();
		transactionDto.setAmount(new BigDecimal(1000000));
		transactionDto.setBenificiaryAccount(TestData.ACCOUNT_NUMBER);
		transactionDto.setFromAccount(TestData.ACCOUNT_NUMBER_1);

		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER)).thenReturn(Optional.empty());
		when(accountRepository.findByAccountNumber(TestData.ACCOUNT_NUMBER_1)).thenReturn(Optional.of(fromAccount));

		assertThrows(AccountNumberNotFoundException.class, () -> accountService.initiateTransaction(transactionDto));

	}

}
