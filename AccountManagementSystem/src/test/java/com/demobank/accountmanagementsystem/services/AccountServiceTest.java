package com.demobank.accountmanagementsystem.services;

import static com.demobank.accountmanagementsystem.testdata.AccountTestData.ACCOUNT_NUMBER_100001L;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.AVAILABLE_BALANCE;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.IFSC_CODE_IFSC0001;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demobank.accountmanagementsystem.constants.AccountStatus;
import com.demobank.accountmanagementsystem.constants.AccountType;
import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.repositories.AccountRepository;

@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@MockBean
	AccountRepository accountRepository;

	Account account, accountInactive, accountDeleted;

	@BeforeEach
	public void setUpTestData() {
		account = new Account();
		account.setAccountNumber(ACCOUNT_NUMBER_100001L);
		account.setAccountType(AccountType.SAVINGS.toString());
		account.setAccountStatus(AccountStatus.ACTIVE.toString());
		account.setAvailableBalance(AVAILABLE_BALANCE);
		account.setIfsc(IFSC_CODE_IFSC0001);

		accountInactive = new Account();
		accountInactive.setAccountNumber(ACCOUNT_NUMBER_100001L);
		accountInactive.setAccountType(AccountType.SAVINGS.toString());
		accountInactive.setAccountStatus(AccountStatus.INACTIVE.toString());
		accountInactive.setAvailableBalance(AVAILABLE_BALANCE);
		accountInactive.setIfsc(IFSC_CODE_IFSC0001);

		accountDeleted = new Account();
		accountDeleted.setAccountNumber(ACCOUNT_NUMBER_100001L);
		accountDeleted.setAccountType(AccountType.SAVINGS.toString());
		accountDeleted.setAccountStatus(AccountStatus.DELETED.toString());
		accountDeleted.setAvailableBalance(AVAILABLE_BALANCE);
		accountDeleted.setIfsc(IFSC_CODE_IFSC0001);

	}

	@Test
	public void testCreate() {
		when(accountRepository.save(account)).thenReturn(account);

		Account savedAccount = accountService.createAccount(account);

		assertThat(savedAccount.getAccountNumber()).isEqualTo(account.getAccountNumber());
		assertThat(savedAccount.getAccountStatus()).isEqualTo(account.getAccountStatus());
		assertThat(savedAccount.getAvailableBalance()).isEqualTo(account.getAvailableBalance());
	}

	@Test
	public void testUpdateAccount() throws AccountNumberNotFoundException {
		when(accountRepository.save(account)).thenReturn(accountInactive);

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);

		Account updatedAccount = accountService.updateAccount(account.getAccountNumber(), accountInactive);

		assertThat(updatedAccount.getAccountNumber()).isEqualTo(accountInactive.getAccountNumber());
		assertThat(updatedAccount.getAccountStatus()).isEqualTo(accountInactive.getAccountStatus());
		assertThat(updatedAccount.getAvailableBalance()).isEqualTo(accountInactive.getAvailableBalance());
	}

	@Test
	public void testUpdateAccountThrowsAccountNumberNotFoundException() {

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(null);

		Assertions.assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.updateAccount(account.getAccountNumber(), accountInactive);
		});

	}

	@Test
	public void testDeleteAccount() {
		when(accountRepository.save(account)).thenReturn(accountDeleted);

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);

		Account updatedAccount = accountService.deleteAccount(account.getAccountNumber());

		assertThat(updatedAccount.getAccountNumber()).isEqualTo(accountDeleted.getAccountNumber());
		assertThat(updatedAccount.getAccountStatus()).isEqualTo(accountDeleted.getAccountStatus());

	}

	@Test
	public void testDeleteAccountThrowsAccountNumberNotFoundException() {

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(null);

		Assertions.assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.deleteAccount(account.getAccountNumber());
		});

	}

	@Test
	public void testFetchAccountByAccountNumber() {

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);

		Account acc = accountService.fetchAccountByAccountNumber(account.getAccountNumber());

		assertThat(acc.getAccountNumber()).isEqualTo(account.getAccountNumber());
		assertThat(acc.getAccountStatus()).isEqualTo(account.getAccountStatus());
		assertThat(acc.getAvailableBalance()).isEqualTo(account.getAvailableBalance());

	}
	@Test
	public void testFetchAccountByAccountNumberNotExists() {

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(null);

		

		Assertions.assertThrows(AccountNumberNotFoundException.class, () -> {
			 accountService.fetchAccountByAccountNumber(account.getAccountNumber());
		});
		
		

	}

	
	@Test
	public void testFetchAccountsByIfscCode()
	{
		List<Account> accounts= new ArrayList<Account>();
		accounts.add(account);
		accounts.add(accountInactive);
		
		when(accountRepository.findByIfsc(IFSC_CODE_IFSC0001)).thenReturn(accounts);
		
		List<Account> accountList= accountService.fetchAccountsByIfscCode(IFSC_CODE_IFSC0001);

		assertFalse(accountList.isEmpty());
		assertThat(accountList.size()).isEqualTo(accounts.size());
		
	}
	
	@Test
	public void testFetchAccountsByIfscCodeThrowsAccountNumberNotFoundException()
	{
		List<Account> accounts= new ArrayList<Account>();
		
		
		when(accountRepository.findByIfsc(IFSC_CODE_IFSC0001)).thenReturn(accounts);
		
		Assertions.assertThrows(AccountNumberNotFoundException.class, () -> {
			accountService.fetchAccountsByIfscCode(IFSC_CODE_IFSC0001);
		});

		
		
	}
}
