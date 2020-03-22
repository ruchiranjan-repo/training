package com.hcl.banking.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hcl.banking.constants.AccountStatus;
import com.hcl.banking.constants.TransactionStatus;
import com.hcl.banking.constants.TransactionType;
import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.AccountNotFoundWithIFSCCode;
import com.hcl.banking.exceptions.AccountNumberNotFoundException;
import com.hcl.banking.exceptions.TransactionFailedException;
import com.hcl.banking.mapper.Mapper;
import com.hcl.banking.models.Account;
import com.hcl.banking.models.Transaction;
import com.hcl.banking.repositories.AccountRepository;

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
		return mapper.mapAccountToAccountDTO(acc);

	}

	@Override
	public AccountDTO updateAccount(Long accountNumber, AccountDTO accountDto) throws AccountNumberNotFoundException {
		Optional<Account> acc = accountRepository.findByAccountNumber(accountNumber);
		if (acc.isPresent()) {
			acc.get().setAccountStatus(accountDto.getAccountStatus());
			acc.get().setAvailableBalance(accountDto.getAvailableBalance());
			Account updatedAccount = accountRepository.save(acc.get());
			log.info("Account details updated successfully for account number :" + accountNumber);
			return mapper.mapAccountToAccountDTO(updatedAccount);
		} else {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException(accountNumber);
		}

	}

	@Override
	public AccountDTO deleteAccount(Long accountNumber) throws AccountNumberNotFoundException {
		Optional<Account> acc = accountRepository.findByAccountNumber(accountNumber);
		if (acc.isPresent()) {
			acc.get().setAccountStatus(AccountStatus.DELETED.toString());
			Account deletedAccount = accountRepository.save(acc.get());
			log.info("Account with account number:" + accountNumber + "deleted successfully.");
			return mapper.mapAccountToAccountDTO(deletedAccount);
		} else {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException(accountNumber);
		}
	}

	@Override
	public AccountDTO fetchAccountByAccountNumber(Long accountNumber) throws AccountNumberNotFoundException {
		Optional<Account> acc = accountRepository.findByAccountNumber(accountNumber);
		if (!acc.isPresent()) {
			log.warn("Account with account number:" + accountNumber + "not Found.");
			throw new AccountNumberNotFoundException(accountNumber);
		}
		log.info("Account with account number:" + accountNumber + "deleted successfully.");
		return mapper.mapAccountToAccountDTO(acc.get());
	}

	@Override
	public List<AccountDTO> fetchAccountsByIfscCode(String ifscCode) throws AccountNumberNotFoundException {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByIfsc(ifscCode);
		if (CollectionUtils.isEmpty(accounts)) {
			log.warn("Account with ifsc code:" + ifscCode + "not Found.");
			throw new AccountNotFoundWithIFSCCode(ifscCode);
		}
		log.info("Accounts with ifsc code:" + ifscCode + " found. Number of accounts:" + accounts.size());
		return mapper.mapAccountListToAccoutDTOs(accounts);
	}

	@Override

	public void initiateTransaction(TransactionDTO transactionDto) throws TransactionFailedException {

		log.info("Transaction initiated");
		Long benificiaryAccountNumber = transactionDto.getBenificiaryAccount();
		Long fromAccountNumber = transactionDto.getFromAccount();
		BigDecimal amount = transactionDto.getAmount();
		log.info("Transaction initiated from account:" + fromAccountNumber + "to : " + benificiaryAccountNumber
				+ "for amount :" + amount);

		Optional<Account> benificiaryAccount = accountRepository.findByAccountNumber(benificiaryAccountNumber);
		Optional<Account> fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
		// AccountDTO benificiaryAccount =
		// fetchAccountByAccountNumber(benificiaryAccountNumber);
		// AccountDTO fromAccount = fetchAccountByAccountNumber(fromAccountNumber);
		if (fromAccount.isPresent()) {
			if (benificiaryAccount.isPresent()) {

				BigDecimal fromBalance = fromAccount.get().getAvailableBalance();
				if (fromBalance.compareTo(amount) == 1) {

					/*Transaction fromAccountTransaction = new Transaction();
					fromAccountTransaction.setAmount(amount);
					fromAccountTransaction.setBenificiaryAccount(benificiaryAccountNumber);
					fromAccountTransaction.setFromAccount(fromAccountNumber);
					fromAccountTransaction.setTransactionDate(LocalDateTime.now());
					fromAccountTransaction.setTransactionStatus("SUCCESS");
					fromAccountTransaction.setTransactionType("DEBIT");
					fromAccountTransaction.setAccount(fromAccount.get());*/
					
					Transaction fromAccountTransaction =new Transaction( fromAccountNumber, benificiaryAccountNumber, TransactionType.DEBIT.toString(),
							LocalDateTime.now(),amount ,TransactionStatus.SUCCESS.toString(),fromAccount.get() );

					fromAccount.get().setAvailableBalance(fromBalance.subtract(amount));
					List<Transaction> transactionListFromAccount = new ArrayList<Transaction>();

					if (!CollectionUtils.isEmpty(fromAccount.get().getTransaction())) {
						transactionListFromAccount = fromAccount.get().getTransaction();
					}

					transactionListFromAccount.add(fromAccountTransaction);
					fromAccount.get().setTransaction(transactionListFromAccount);

					/*
					 * Transaction benificiaryAccountTransaction = new Transaction();
					 * benificiaryAccountTransaction.setAmount(amount);
					 * benificiaryAccountTransaction.setFromAccount(fromAccountNumber);
					 * benificiaryAccountTransaction.setBenificiaryAccount(benificiaryAccountNumber)
					 * ; benificiaryAccountTransaction.setTransactionDate(LocalDateTime.now());
					 * benificiaryAccountTransaction.setTransactionStatus("SUCCESS");
					 * benificiaryAccountTransaction.setTransactionType("CREDIT");
					 * benificiaryAccountTransaction.setAccount(benificiaryAccount.get());
					 */
					
					
					Transaction benificiaryAccountTransaction =new Transaction( fromAccountNumber, benificiaryAccountNumber, TransactionType.CREDIT.toString(),
							LocalDateTime.now(),amount ,TransactionStatus.SUCCESS.toString(),benificiaryAccount.get() );
					
					

					
					benificiaryAccount.get()
							.setAvailableBalance(benificiaryAccount.get().getAvailableBalance().add(amount));
					List<Transaction> transactionListBenificiaryAccount = new ArrayList<Transaction>();
					if (!CollectionUtils.isEmpty(benificiaryAccount.get().getTransaction())) {
						transactionListBenificiaryAccount = benificiaryAccount.get().getTransaction();
					}

					transactionListBenificiaryAccount.add(benificiaryAccountTransaction);
					benificiaryAccount.get().setTransaction(transactionListBenificiaryAccount);

					accountRepository.saveAndFlush(fromAccount.get());

					accountRepository.saveAndFlush(benificiaryAccount.get());
					log.info("Amount:" + amount + " transfered successfully from account:" + fromAccountNumber + "to : "
							+ benificiaryAccountNumber);

				} else {
					
					Transaction fromAccountTransaction =new Transaction( fromAccountNumber, benificiaryAccountNumber, TransactionType.DEBIT.toString(),
							LocalDateTime.now(),amount ,TransactionStatus.FAILED.toString(),fromAccount.get() );
					List<Transaction> transactionListFromAccount = new ArrayList<Transaction>();
					if (!CollectionUtils.isEmpty(fromAccount.get().getTransaction())) {
						transactionListFromAccount = fromAccount.get().getTransaction();
					}
					transactionListFromAccount.add(fromAccountTransaction);
					fromAccount.get().setTransaction(transactionListFromAccount);
					
					
					Transaction benificiaryAccountTransaction =new Transaction( fromAccountNumber, benificiaryAccountNumber, TransactionType.CREDIT.toString(),
							LocalDateTime.now(),amount ,TransactionStatus.FAILED.toString(),benificiaryAccount.get() );
					List<Transaction> transactionListBenificiaryAccount = new ArrayList<Transaction>();
					if (!CollectionUtils.isEmpty(benificiaryAccount.get().getTransaction())) {
						transactionListBenificiaryAccount = benificiaryAccount.get().getTransaction();
					}

					transactionListBenificiaryAccount.add(benificiaryAccountTransaction);
					benificiaryAccount.get().setTransaction(transactionListBenificiaryAccount);
					
					accountRepository.saveAndFlush(fromAccount.get());
					accountRepository.saveAndFlush(benificiaryAccount.get());
					log.warn("Not sufficient balance. Transaction Failed");
					throw new TransactionFailedException(fromAccountNumber);
				}

			} else {
				log.warn("Account with account number:" + benificiaryAccountNumber + " not found.");
				throw new AccountNumberNotFoundException(benificiaryAccountNumber);
			}
		} else {
			log.warn("Account with account number:" + fromAccountNumber + " not found.");
			throw new AccountNumberNotFoundException(fromAccountNumber);
		}
	}
}
