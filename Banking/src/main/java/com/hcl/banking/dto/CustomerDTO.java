package com.hcl.banking.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value=Include.NON_NULL)
public class CustomerDTO {

	Long CustomerId;

	String customerName;

	String customerContact;

	String customerEmail;
	LocalDate customerDob;
	@JsonInclude(Include.NON_EMPTY)
	List<AddressDTO> customerAddress = new ArrayList<>();
	@JsonInclude(Include.NON_EMPTY)
	List<AccountDTO> accounts = new ArrayList<>();

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public LocalDate getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(LocalDate customerDob) {
		this.customerDob = customerDob;
	}

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<AddressDTO> getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(List<AddressDTO> customerAddress) {
		this.customerAddress = customerAddress;
	}

}
