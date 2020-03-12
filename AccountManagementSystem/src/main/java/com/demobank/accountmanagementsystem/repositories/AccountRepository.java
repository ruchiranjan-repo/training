package com.demobank.accountmanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demobank.accountmanagementsystem.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	
	Account findByAccountNumber(Long accountNumber);
	
	List<Account> findByIfsc(String ifsc);

}
