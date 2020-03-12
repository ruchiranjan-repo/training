package com.demobank.accountmanagementsystem.repositories;

import static com.demobank.accountmanagementsystem.testdata.AccountTestData.ACCOUNT_NUMBER_100001L;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.ACCOUNT_NUMBER_100L;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.AVAILABLE_BALANCE;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.IFSC_CODE_IFSC0001;
import static com.demobank.accountmanagementsystem.testdata.AccountTestData.IFSC_CODE_IFSC0002;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.demobank.accountmanagementsystem.constants.AccountStatus;
import com.demobank.accountmanagementsystem.constants.AccountType;
import com.demobank.accountmanagementsystem.models.Account;

@DataJpaTest
public class AccountRepositoryTest {
	
	
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	AccountRepository accountRepository;
	
	Account account, account1;
	
	
	@BeforeEach
	public void setUpTestData()
	{
		account= new Account();
		account.setAccountNumber(ACCOUNT_NUMBER_100001L);
		account.setAccountType(AccountType.SAVINGS.toString());
		account.setAccountStatus(AccountStatus.ACTIVE.toString());		
		account.setAvailableBalance(AVAILABLE_BALANCE);
		account.setIfsc(IFSC_CODE_IFSC0001);
		
		account1= new Account();
		account1.setAccountNumber(ACCOUNT_NUMBER_100L);
		account1.setAccountType(AccountType.SAVINGS.toString());
		account1.setAccountStatus(AccountStatus.ACTIVE.toString());		
		account1.setAvailableBalance(AVAILABLE_BALANCE);
		account1.setIfsc(IFSC_CODE_IFSC0001);
	}
	
	
	@Test
	public void testFindByAccountNumber()
	{
		testEntityManager.persistAndFlush(account);	
		
		Account account=accountRepository.findByAccountNumber(ACCOUNT_NUMBER_100001L);
		
		assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_100001L);
		assertThat(account.getAccountType()).isEqualTo(AccountType.SAVINGS.toString());
		assertThat(account.getAvailableBalance()).isEqualTo(AVAILABLE_BALANCE);
		
		
	}
	
	@Test
	public void testFindByAccountNumberNotExists()
	{
		testEntityManager.persistAndFlush(account);	
		
		Account account=accountRepository.findByAccountNumber(ACCOUNT_NUMBER_100L);		
		
		assertNull(account);
		
	}
	
	@Test
	public void testFindByIfscCode()
	{
		
		List<Account> accounts= new ArrayList<Account>();
		accounts.add(account);
		accounts.add(account1);
		
		testEntityManager.persistAndFlush(account);	
		testEntityManager.persistAndFlush(account1);	
		
		List<Account> accountList=accountRepository.findByIfsc(IFSC_CODE_IFSC0001);
		
		
		assertFalse(accountList.isEmpty());
		assertThat(accountList.size()).isEqualTo(accounts.size());
		
	}
	

	@Test
	public void testFindByIfscCodeNotExists()
	{
		
		List<Account> accounts= new ArrayList<Account>();
		accounts.add(account);
		accounts.add(account1);
		
		testEntityManager.persistAndFlush(account);	
		testEntityManager.persistAndFlush(account1);	
		
		List<Account> accountList=accountRepository.findByIfsc(IFSC_CODE_IFSC0002);
		
		
		assertTrue(accountList.isEmpty());
		assertThat(accountList.size()).isEqualTo(0);
		
	}
	
	
	
	
	

}
