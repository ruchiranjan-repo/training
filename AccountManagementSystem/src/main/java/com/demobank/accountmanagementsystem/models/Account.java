package com.demobank.accountmanagementsystem.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.demobank.accountmanagementsystem.constants.AccountManagementConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Account")

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long accountNumber;

	@Column(name = "ifsc")
	@NotNull
	String ifsc = AccountManagementConstants.IFSC_CODE;

	@Column(name = "AccountType")
	@NotNull
	String accountType;

	@Column(name = "AvailableBalance")
	@NotNull
	BigDecimal availableBalance;

	@Column(name = "AccountStatus")
	@NotNull
	String accountStatus;

	 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	 @JoinColumn(name="accountNumber")
	 List<Transaction> transaction;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 @JoinColumn(name="CustomerId")
	 Customer customer;
	

	public Account() {

	}

	

	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	
	

}
