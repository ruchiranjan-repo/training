package com.demobank.accountmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demobank.accountmanagementsystem.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	

}
