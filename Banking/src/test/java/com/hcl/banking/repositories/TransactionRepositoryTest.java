package com.hcl.banking.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.hcl.banking.constants.TransactionStatus;
import com.hcl.banking.constants.TransactionType;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Transaction;

@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	TransactionRepository transactionRepository;
	private Account benificiaryAccount, fromAccount;
	
	
	

	@BeforeEach
	public void setup() {

		benificiaryAccount = new Account();
		benificiaryAccount.setAccountNumber(TestData.ACCOUNT_NUMBER);
		benificiaryAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		benificiaryAccount.setAccountType(AccountType.SAVINGS.toString());
		benificiaryAccount.setAvailableBalance(new BigDecimal(10000));
		benificiaryAccount.setIfsc(TestData.IFSC_CODE);
		Transaction benificiaryAccountTransactionSuccess = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(),TestData.AMOUNT_TRANSAFERED , TransactionStatus.SUCCESS.toString(),
				benificiaryAccount);
		
		Transaction benificiaryAccountTransactionFailed = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.CREDIT.toString(), LocalDateTime.now(),TestData.AMOUNT_TRANSAFERED , TransactionStatus.FAILED.toString(),
				benificiaryAccount);
		
		List<Transaction> transactions= new ArrayList<Transaction>();
		transactions.add(benificiaryAccountTransactionSuccess);
		transactions.add(benificiaryAccountTransactionFailed);
		benificiaryAccount.setTransaction(transactions);
		
		fromAccount = new Account();
		fromAccount.setAccountNumber(TestData.ACCOUNT_NUMBER_1);
		fromAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		fromAccount.setAccountType(AccountType.SAVINGS.toString());
		fromAccount.setAvailableBalance(new BigDecimal(10000));
		fromAccount.setIfsc(TestData.IFSC_CODE);
		Transaction fromAccountTransactionSuccess = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.DEBIT.toString(), LocalDateTime.now(),TestData.AMOUNT_TRANSAFERED , TransactionStatus.SUCCESS.toString(),
				fromAccount);
		Transaction fromAccountTransactionFailed = new Transaction(TestData.ACCOUNT_NUMBER_1, TestData.ACCOUNT_NUMBER,
				TransactionType.DEBIT.toString(), LocalDateTime.now(),TestData.AMOUNT_TRANSAFERED , TransactionStatus.FAILED.toString(),
				fromAccount);
		List<Transaction> transactionList= new ArrayList<Transaction>();
		transactionList.add(fromAccountTransactionSuccess);
		transactionList.add(fromAccountTransactionFailed);
		fromAccount.setTransaction(transactions);		

	}
	
	
	@Test
	public void testFindByAccount_accountId()
	{ 
		Account benificiaryAcc=testEntityManager.persist(benificiaryAccount);
		
		 List<Transaction> transactions= transactionRepository.findByAccount_accountId(benificiaryAcc.getAccountId());
		
		assertFalse(CollectionUtils.isEmpty(transactions));
		assertThat(transactions.size()).isEqualTo(benificiaryAcc.getTransaction().size());
		
		
	}
	
	@Test
	public void testFindByAccount_accountIdNotFound()
	{ 
		testEntityManager.persist(benificiaryAccount);
		
		 List<Transaction> transactions= transactionRepository.findByAccount_accountId(TestData.ACCOUNT_NUMBER_1);
		
		assertTrue(CollectionUtils.isEmpty(transactions));
		
		
		
	}
	
	
	 @Test
		public void testFindByTransactionReference()
		{ 
			Account benificiaryAcc=testEntityManager.persist(benificiaryAccount);
			Long transactionReference=benificiaryAcc.getTransaction().get(0).getTransactionReference();
			
			 Optional<Transaction> transaction= transactionRepository.findByTransactionReference(transactionReference);
			
			 assertTrue(transaction.isPresent());
			
			assertThat(transaction.get().getTransactionReference()).isEqualTo(transactionReference);
			
			
		}
		
		@Test
		public void testFindByTransactionReferenceNotFound()
		{ 
			testEntityManager.persist(benificiaryAccount);
			
			Optional<Transaction> transaction= transactionRepository.findByTransactionReference(TestData.ACCOUNT_NUMBER_1);
			
			assertFalse(transaction.isPresent());
			
			
			
		}
}
