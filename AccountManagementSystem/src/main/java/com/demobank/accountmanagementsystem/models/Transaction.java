package com.demobank.accountmanagementsystem.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")

public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long transactionReference ;
	
	
	@Column(name="BenificiaryAccount")
	Long benificiaryAccount;
	
	
	@Column(name="transactionType")
	String transactionType;
	
	@Column(name="transactionDate")
	LocalDateTime transactionDate;
	
	
	@Column(name="updatedAccountBalance")
	BigDecimal amount;
	
	@Column(name="fromAccount")
	Long fromAccount;
	
	@Column(name="transactionStatus")
	String transactionStatus;

	
	
	
	public Long getTransactionReference() {
		return transactionReference;
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
	
	

}
