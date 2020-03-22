package com.hcl.banking.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long transactionReference;

	@Column(name = "fromAccount")
	Long fromAccount;

	@Column(name = "BenificiaryAccount")
	Long benificiaryAccount;

	@Column(name = "transactionType")
	String transactionType;

	@Column(name = "transactionDate")
	LocalDateTime transactionDate;

	@Column(name = "updatedAccountBalance")
	BigDecimal amount;

	@Column(name = "transactionStatus")
	String transactionStatus;

	@ManyToOne
	@JoinColumn(name = "accountId")
	Account account;

	public Long getTransactionReference() {
		return transactionReference;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setTransactionReference(Long transactionReference) {
		this.transactionReference = transactionReference;
	}

	public Long getBenificiaryAccount() {
		return benificiaryAccount;
	}

	public void setBenificiaryAccount(Long benificiaryAccount) {
		this.benificiaryAccount = benificiaryAccount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Transaction( Long fromAccount, Long benificiaryAccount, String transactionType,
			LocalDateTime transactionDate, BigDecimal amount, String transactionStatus, Account account) {
		super();
	
		this.fromAccount = fromAccount;
		this.benificiaryAccount = benificiaryAccount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
		this.account = account;
	}
	
	public Transaction()
	{
		
	}

	
}
