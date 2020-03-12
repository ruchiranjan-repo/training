package com.demobank.accountmanagementsystem.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.demobank.accountmanagementsystem.constants.AccountManagementConstants;

@Entity
@Table(name="Account")
public class Account implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id	
	Long accountNumber;
	
	@Column(name="ifsc")
	@NotNull
	String ifsc = AccountManagementConstants.IFSC_CODE;
	
	@Column(name="AccountType")
	@NotNull
	String accountType;
	
	@Column(name="AvailableBalance")
	@NotNull
	BigDecimal availableBalance;
	
	@Column(name="AccountStatus")
	@NotNull
	String accountStatus;

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
		
	

}
