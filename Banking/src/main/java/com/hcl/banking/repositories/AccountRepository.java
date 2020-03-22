package com.hcl.banking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.banking.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	Optional<Account> findByAccountId(Long accountId);
	
	Optional<Account> findByAccountNumber(Long accountNumber);
	
	List<Account> findByIfsc(String ifsc);

}
