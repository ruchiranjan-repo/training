package com.hcl.banking.models;

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

import com.hcl.banking.constants.AccountManagementConstants;

@Entity
@Table(name = "Account")

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long accountId;
	
	@Column(name="accountNumber")
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

	 @OneToMany(fetch = FetchType.LAZY,mappedBy="account",cascade =CascadeType.ALL)		 
	 List<Transaction> transaction;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 @JoinColumn(name="CustomerId")
	 Customer customer;
	

	public Account() {

	}

	

	public Account(Long accountNumber, String ifsc,  String accountType,
			 BigDecimal availableBalance, String accountStatus, List<Transaction> transaction,
			Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.accountType = accountType;
		this.availableBalance = availableBalance;
		this.accountStatus = accountStatus;
		this.transaction = transaction;
		this.customer = customer;
	}



	public Long getAccountId() {
		return accountId;
	}



	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
