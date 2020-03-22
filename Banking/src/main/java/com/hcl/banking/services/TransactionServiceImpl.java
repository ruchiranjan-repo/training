package com.hcl.banking.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hcl.banking.dto.AccountDTO;
import com.hcl.banking.dto.TransactionDTO;
import com.hcl.banking.exceptions.TransactionFailedException;
import com.hcl.banking.exceptions.TransactionNotFoundException;
import com.hcl.banking.mapper.Mapper;
import com.hcl.banking.models.Transaction;
import com.hcl.banking.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountService accountService;
	
	@Autowired
	Mapper mapper;

	Logger log = Logger.getLogger(TransactionServiceImpl.class);

	@Override
	public List<TransactionDTO> getTransactionsByAccountNumber(Long accountNumber) {
		AccountDTO accountDTO = accountService.fetchAccountByAccountNumber(accountNumber);
		if(accountDTO.getAccountId()!=null)
		{
		 
		List<Transaction> transactions = transactionRepository.findByAccount_accountId(accountDTO.getAccountId());
		if (!CollectionUtils.isEmpty(transactions)) {
			log.info("Number of transactions for account Number " + accountNumber + "  is :" + transactions.size());
			return mapper.mapTransactionToTransactionDtos(transactions);
		}
		}
		log.warn("No transaction found with account number: " + accountNumber);
		throw new TransactionNotFoundException(accountNumber);
	}

	@Override
	public TransactionDTO getTransactionByTransactionReference(Long transactionReference) {
		Optional<Transaction> transaction = transactionRepository.findByTransactionReference(transactionReference);
		if (transaction.isPresent()) {

			return mapper.mapTransactionDTOToTransaction(transaction.get());
		}

		log.warn("No transaction found with transaction reference: " + transactionReference);
		throw new TransactionNotFoundException(transactionReference);

	}

	@Override
	public void initiateTransaction(TransactionDTO transaction) throws TransactionFailedException {
		accountService.initiateTransaction(transaction);

	}

}
