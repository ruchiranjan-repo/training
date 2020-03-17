package com.demobank.accountmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demobank.accountmanagementsystem.models.*;

@Repository
public interface CustomerRepository extends JpaRepository<com.demobank.accountmanagementsystem.models.Customer, Long> {

	

	Customer findByCustomerId(Long customerId);
}
