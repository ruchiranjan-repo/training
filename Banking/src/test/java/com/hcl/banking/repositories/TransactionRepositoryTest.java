package com.hcl.banking.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.hcl.banking.constant.TestData;
import com.hcl.banking.constants.AccountStatus;
import com.hcl.banking.constants.AccountType;
import com.hcl.banking.constants.TransactionStatus;
import com.hcl.banking.constants.TransactionType;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Address;
import com.hcl.banking.models.Customer;
import com.hcl.banking.models.Transaction;

@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	TransactionRepository transactionRepository;
	private Account benificiaryAccount, fromAccount;
	@BeforeEach
	public void setup()
	{
		
		
		/*
		 * benificiaryAccount= new Account();
		 * benificiaryAccount.setAccountNumber(TestData.ACCOUNT_NUMBER);
		 * benificiaryAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		 * benificiaryAccount.setAccountType(AccountType.SAVINGS.toString());
		 * benificiaryAccount.setAvailableBalance(new BigDecimal(10000));
		 * benificiaryAccount.setIfsc(TestData.IFSC_CODE); Transaction
		 * fromAccountTransaction =new Transaction( 100L, benificiaryAccountNumber,
		 * TransactionType.DEBIT.toString(), LocalDateTime.now(),amount
		 * ,TransactionStatus.SUCCESS.toString(),fromAccount.get() );
		 */
	}
	
}
