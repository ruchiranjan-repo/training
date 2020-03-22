package com.hcl.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

	Long transactionReference;
	Long benificiaryAccount;
	String transactionType;
	LocalDateTime transactionDate;
	BigDecimal amount;
	Long fromAccount;
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
//

	public Long getFromAccount() {
		return fromAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
