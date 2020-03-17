package com.demobank.accountmanagementsystem.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long customerId;

	@Column(name = "customerName")
	@NotNull
	String customerName;

	@Column(name = "customerContact")
	@Size(min = 10, max = 13)
	@NotNull
	String customerContact;

	@Column(name = "customerEmail")
	@NotNull
	String customerEmail;

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_address")
	List<Address> customerAddress = new ArrayList<Address>();

	@Column(name = "customerDob")
	@NotNull
	LocalDate customerDob;

	 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JoinColumn(name="CustomerId")
	List<Account> accounts= new ArrayList<Account>();

	public Customer() {

	}

	public Customer(Long customerId, String customerName, String customerContact, String customerEmail,
			List<Address> customerAddress, LocalDate customerDob, List<Account> accounts) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerDob = customerDob;
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public LocalDate getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(LocalDate customerDob) {
		this.customerDob = customerDob;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<Address> getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(List<Address> customerAddress) {
		this.customerAddress = customerAddress;
	}
	

}
