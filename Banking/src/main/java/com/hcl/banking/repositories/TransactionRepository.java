package com.hcl.banking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.banking.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
   List<Transaction> findByAccount_accountId(Long accountId);
   
   Optional<Transaction> findByTransactionReference(Long transactionReference);

}
