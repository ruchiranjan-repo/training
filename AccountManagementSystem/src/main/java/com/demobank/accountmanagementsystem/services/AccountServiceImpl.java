package com.demobank.accountmanagementsystem.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demobank.accountmanagementsystem.constants.AccountStatus;
import com.demobank.accountmanagementsystem.dto.AccountDTO;
import com.demobank.accountmanagementsystem.dto.TransactionDTO;
import com.demobank.accountmanagementsystem.exceptions.AccountNumberNotFoundException;
import com.demobank.accountmanagementsystem.exceptions.TransactionFailedException;
import com.demobank.accountmanagementsystem.mapper.Mapper;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	Mapper mapper;

	Logger log = Logger.getLogger(AccountServiceImpl.class);

	@Override
	public AccountDTO createAccount(AccountDTO account) {
		Account acc = accountRepository.save(mapper.mapAccountDTOToAccount(account));
		log.info("Account created successfully");
		// account.setAccountNumber(AccountManagementUtils.accountNumberGenerator());
		return mapper.mapAccountToAccountDTO(acc);

	}

	@Override
	public AccountDTO updateAccount(Long accountNumber, AccountDTO accountDto) throws AccountNumberNotFoundException {
		Account acc = accountRepository.findByAccountNumber(accountNumber);
		if (acc != null) {
			acc.setAccountStatus(accountDto.getAccountStatus());
			acc.setAvailableBalance(accountDto.getAvailableBalance());
			Account updatedAccount=accountRepository.save(acc);
			log.info("Account details updated successfully for account number :" + accountNumber);
			return mapper.mapAccountToAccountDTO(updatedAccount);
		} else {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException();
		}

	}

	@Override
	public AccountDTO deleteAccount(Long accountNumber) throws AccountNumberNotFoundException {
		Account acc = accountRepository.findByAccountNumber(accountNumber);
		if (acc != null) {
			acc.setAccountStatus(AccountStatus.DELETED.toString());
			Account deletedAccount=accountRepository.save(acc);
            log.info("Account with account number:" + accountNumber + "deleted successfully.");
			return mapper.mapAccountToAccountDTO(deletedAccount);
		} else {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException();
		}
	}

	@Override
	public AccountDTO fetchAccountByAccountNumber(Long accountNumber) throws AccountNumberNotFoundException {
		Account acc = accountRepository.findByAccountNumber(accountNumber);
		if (acc == null) {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException();
		}
		 log.info("Account with account number:" + acc.getAccountNumber() + "deleted successfully.");
		return mapper.mapAccountToAccountDTO(acc);
	}

	@Override
	public List<AccountDTO> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByIfsc(ifscCode);
		if (CollectionUtils.isEmpty(accounts)) {
			log.warn("Account with ifsc code:" + ifscCode + "not Found.");
			throw new AccountNumberNotFoundException();
		}
		log.info("Accounts with ifsc code:"+ifscCode+"found. Number of accounts:"+accounts.size());
		return mapper.mapAccountListToAccoutDTOs(accounts);
	}

	@Override
	
	public void initiateTransaction(TransactionDTO transactionDto) throws TransactionFailedException {

		log.info("Transaction initiated");
		Long benificiaryAccountNumber = transactionDto.getBenificiaryAccount();		
		Long fromAccountNumber = transactionDto.getFromAccount();
		BigDecimal amount = transactionDto.getAmount();
		log.info("Transaction initiated from account:"+fromAccountNumber+"to : "+benificiaryAccountNumber +"for amount :"+amount);

		AccountDTO benificiaryAccount = fetchAccountByAccountNumber(benificiaryAccountNumber);
		AccountDTO fromAccount = fetchAccountByAccountNumber(fromAccountNumber);

		BigDecimal fromBalance = fromAccount.getAvailableBalance();
		if (fromBalance.compareTo(amount) == 1) {

			TransactionDTO fromAccountTransaction = new TransactionDTO();
			fromAccountTransaction.setAmount(amount);
			fromAccountTransaction.setBenificiaryAccount(benificiaryAccountNumber);
			fromAccountTransaction.setTransactionDate(LocalDateTime.now());
			fromAccountTransaction.setTransactionStatus("SUCCESS");
			fromAccountTransaction.setTransactionType("DEBIT");

			List<TransactionDTO> transactionListFromAccount = new ArrayList<>();
			transactionListFromAccount.add(fromAccountTransaction);
			fromAccount.setAvailableBalance(fromBalance.subtract(amount));
			fromAccount.setTransactionDto(transactionListFromAccount);

			TransactionDTO benificiaryAccountTransaction = new TransactionDTO();
			benificiaryAccountTransaction.setAmount(amount);
			benificiaryAccountTransaction.setFromAccount(fromAccountNumber);
			benificiaryAccountTransaction.setTransactionDate(LocalDateTime.now());
			benificiaryAccountTransaction.setTransactionStatus("SUCCESS");
			benificiaryAccountTransaction.setTransactionType("CREDIT");

			List<TransactionDTO> transactionListBenificiaryAccount = new ArrayList<>();
			transactionListBenificiaryAccount.add(benificiaryAccountTransaction);
			benificiaryAccount.setAvailableBalance(benificiaryAccount.getAvailableBalance().add(amount));
			benificiaryAccount.setTransactionDto(transactionListBenificiaryAccount);

			

			accountRepository.saveAndFlush(mapper.mapAccountDTOToAccount(fromAccount));

			accountRepository.saveAndFlush(mapper.mapAccountDTOToAccount(benificiaryAccount));
			log.info("Amount:"+amount+" transfered successfully from account:"+fromAccountNumber+"to : "+benificiaryAccountNumber );

		} else {
			log.warn("Not sufficient balance. Transaction Failed");
			throw new TransactionFailedException();
		}

	}

}
