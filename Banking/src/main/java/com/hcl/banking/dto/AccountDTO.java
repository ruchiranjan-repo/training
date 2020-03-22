package com.hcl.banking.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hcl.banking.util.AccountManagementUtils;
@JsonInclude(value=Include.NON_NULL)

public class AccountDTO {
     Long accountId;
	Long accountNumber;
	String ifsc;
	String accountType;
	BigDecimal availableBalance;
	String accountStatus;
	@JsonInclude(Include.NON_EMPTY)
	List<TransactionDTO> transactionDto;
	CustomerDTO customer;	
	

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public List<TransactionDTO> getTransactionDto() {
		return transactionDto;
	}

	public void setTransactionDto(List<TransactionDTO> transactionDto) {
		this.transactionDto = transactionDto;
	}

	public Long getAccountNumber() {
		if(this.accountNumber==null)
		{
			this.accountNumber=AccountManagementUtils.accountNumberGenerator();
		}
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

	

}
