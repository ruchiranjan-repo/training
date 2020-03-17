package com.demobank.accountmanagementsystem.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.demobank.accountmanagementsystem.dto.AccountDTO;
import com.demobank.accountmanagementsystem.dto.AddressDTO;
import com.demobank.accountmanagementsystem.dto.CustomerDTO;
import com.demobank.accountmanagementsystem.dto.TransactionDTO;
import com.demobank.accountmanagementsystem.models.Account;
import com.demobank.accountmanagementsystem.models.Address;
import com.demobank.accountmanagementsystem.models.Customer;
import com.demobank.accountmanagementsystem.models.Transaction;


@Component
public class Mapper {
	
	public CustomerDTO mapCustomerToCustomerDto(Customer customer)
	{ CustomerDTO customerDto= new CustomerDTO();
	if(customer!=null) {
	customerDto.setCustomerId(customer.getCustomerId());
	customerDto.setCustomerName(customer.getCustomerName());
	customerDto.setCustomerContact(customer.getCustomerContact());
	customerDto.setCustomerEmail(customer.getCustomerEmail());
	customerDto.setCustomerDob(customer.getCustomerDob());
	customerDto.setCustomerAddress(mapAddressListToAddressDtoList(customer.getCustomerAddress()));
	if(!CollectionUtils.isEmpty(customer.getAccounts()))
	{
		for(Account acc:customer.getAccounts())
		{
			AccountDTO accountDto=new AccountDTO();
			accountDto.setAccountNumber(acc.getAccountNumber());
			accountDto.setAccountStatus(acc.getAccountStatus());
			accountDto.setAccountType(acc.getAccountType());
			accountDto.setAvailableBalance(acc.getAvailableBalance());	
			accountDto.setIfsc(acc.getIfsc());
			accountDto.setTransactionDto(mapTransactionToTransactionDtos(acc.getTransaction()));
			
			customerDto.getAccounts().add(accountDto);
		}
	}
	}
	return customerDto;
	}
	
	
	  public Customer mapCustomerDtoToCustomer(CustomerDTO customerDto) {
		  Customer customer= new Customer();
	  if(customerDto!=null) {
		  customer.setCustomerId(customerDto.getCustomerId());
		  customer.setCustomerName(customerDto.getCustomerName());
		  customer.setCustomerContact(customerDto.getCustomerContact());
		  customer.setCustomerEmail(customerDto.getCustomerEmail());
		  customer.setCustomerDob(customerDto.getCustomerDob());
		  customer.setCustomerAddress(mapAddressDtosToAddressList(customerDto.getCustomerAddress())); 
		 
		  if(!CollectionUtils.isEmpty(customerDto.getAccounts()))
			{
				for(AccountDTO accDto:customerDto.getAccounts())
				{
					Account account=new Account();
					account.setAccountNumber(accDto.getAccountNumber());
					account.setAccountStatus(accDto.getAccountStatus());
					account.setAccountType(accDto.getAccountType());
					account.setAvailableBalance(accDto.getAvailableBalance());	
					account.setIfsc(accDto.getIfsc());
					account.setTransaction(mapTransactionDtosToTransaction(accDto.getTransactionDto()));
					
					customer.getAccounts().add(account);
				}
			}
	  }
	  return customer; }
	 
	
	public AddressDTO mapAddressToAddressBo(Address address)
	{
		AddressDTO addressBo= new AddressDTO();
		
		addressBo.setAddressCity(address.getAddressCity());
		addressBo.setAddressStreet(address.getAddressStreet());
		addressBo.setAddressPin(address.getAddressPin());
		addressBo.setAddressType(address.getAddressType());
		
		return addressBo;
	}
	
	public Address mapAddressDtoToAddress(AddressDTO addressDto)
	{
		Address address= new Address();
		address.setAddressCity(addressDto.getAddressCity());
		address.setAddressStreet(addressDto.getAddressStreet());
		address.setAddressPin(addressDto.getAddressPin());
		address.setAddressType(addressDto.getAddressType());
		
		return address;
	}
	
	public List<AddressDTO> mapAddressListToAddressDtoList(List<Address> addresses)
	{
		List<AddressDTO> addDtos= new ArrayList<AddressDTO>();
		
		if(!CollectionUtils.isEmpty(addresses))
		{
			for(Address add: addresses)
			{
				addDtos.add(mapAddressToAddressBo(add));
				
			}
		}
		return addDtos;
		
	}
	
	public List<Address> mapAddressDtosToAddressList(List<AddressDTO> addressDtos)
	{
		List<Address> address= new ArrayList<Address>();
		
		if(!CollectionUtils.isEmpty(addressDtos))
		{
			for(AddressDTO add: addressDtos)
			{
				address.add(mapAddressDtoToAddress(add));
				
			}
		}
		return address;
		
	}
	
	public AccountDTO mapAccountToAccountDTO(Account account)
	{
	AccountDTO accountDto=new AccountDTO();
	accountDto.setAccountNumber(account.getAccountNumber());
	accountDto.setAccountStatus(account.getAccountStatus());
	accountDto.setAccountType(account.getAccountType());
	accountDto.setAvailableBalance(account.getAvailableBalance());	
	accountDto.setIfsc(account.getIfsc());
	accountDto.setTransactionDto(mapTransactionToTransactionDtos(account.getTransaction()));
	if(account.getCustomer()!=null) {
		CustomerDTO customerDto= new CustomerDTO();
		customerDto.setCustomerId(account.getCustomer().getCustomerId());
		customerDto.setCustomerName(account.getCustomer().getCustomerName());
		customerDto.setCustomerContact(account.getCustomer().getCustomerContact());
		customerDto.setCustomerEmail(account.getCustomer().getCustomerEmail());
		customerDto.setCustomerDob(account.getCustomer().getCustomerDob());
		customerDto.setCustomerAddress(mapAddressListToAddressDtoList(account.getCustomer().getCustomerAddress()));
	
		accountDto.setCustomer(customerDto);
	}
	return accountDto;
	
	}
	
	public List<AccountDTO>  mapAccountListToAccoutDTOs(List<Account> accounts)
	{

		List<AccountDTO> accountDtos= new ArrayList<AccountDTO>();
		
		if(!CollectionUtils.isEmpty(accounts))
		{
			for(Account account: accounts)
			{
				accountDtos.add(mapAccountToAccountDTO(account));
				
			}
		}
		return accountDtos;
		
		
	}
	
	
	public Account mapAccountDTOToAccount(AccountDTO accountDto)
	{
	Account account=new Account();
	account.setAccountNumber(accountDto.getAccountNumber());
	account.setAccountStatus(accountDto.getAccountStatus());
	account.setAccountType(accountDto.getAccountType());
	account.setAvailableBalance(accountDto.getAvailableBalance());	
	account.setIfsc(accountDto.getIfsc());
	account.setTransaction(mapTransactionDtosToTransaction(accountDto.getTransactionDto()));
	if(accountDto.getCustomer()!=null) {
		Customer customer= new Customer();
		customer.setCustomerId(accountDto.getCustomer().getCustomerId());
		customer.setCustomerName(accountDto.getCustomer().getCustomerName());
		customer.setCustomerContact(accountDto.getCustomer().getCustomerContact());
		customer.setCustomerEmail(accountDto.getCustomer().getCustomerEmail());
		customer.setCustomerDob(accountDto.getCustomer().getCustomerDob());
		customer.setCustomerAddress(mapAddressDtosToAddressList(accountDto.getCustomer().getCustomerAddress()));
		
		account.setCustomer(customer);
	}
	return account;
	
	}
	
	public List<Account>  mapAccountDtosToAccouts(List<AccountDTO> accountDtos)
	{

		List<Account> accounts= new ArrayList<Account>();
		
		if(!CollectionUtils.isEmpty(accountDtos))
		{
			for(AccountDTO accountDto: accountDtos)
			{
				accounts.add(mapAccountDTOToAccount(accountDto));
				
			}
		}
		return accounts;
		
		
	}
	
	public Transaction mapTransactionDTOToTransaction(TransactionDTO transactionDto)
	{
		Transaction transaction=new Transaction();
		transaction.setBenificiaryAccount(transactionDto.getBenificiaryAccount());
		transaction.setFromAccount(transactionDto.getFromAccount());
		transaction.setTransactionDate(transactionDto.getTransactionDate());
		transaction.setTransactionReference(transactionDto.getTransactionReference());	
		transaction.setTransactionStatus(transactionDto.getTransactionStatus());
		transaction.setTransactionType(transactionDto.getTransactionType());
		transaction.setAmount(transactionDto.getAmount());
	    return transaction;
	
	}
	
	public TransactionDTO mapTransactionDTOToTransaction(Transaction transaction)
	{
		TransactionDTO transactionDto=new TransactionDTO();
		transactionDto.setBenificiaryAccount(transaction.getBenificiaryAccount());
		transactionDto.setFromAccount(transaction.getFromAccount());
		transactionDto.setTransactionDate(transaction.getTransactionDate());
		transactionDto.setTransactionReference(transaction.getTransactionReference());	
		transactionDto.setTransactionStatus(transaction.getTransactionStatus());
		transactionDto.setTransactionType(transaction.getTransactionType());
		transactionDto.setAmount(transaction.getAmount());
	    return transactionDto;
	
	}
	
	public List<Transaction>  mapTransactionDtosToTransaction(List<TransactionDTO> transactionDtos)
	{

		List<Transaction> transaction= new ArrayList<Transaction>();
		
		if(!CollectionUtils.isEmpty(transactionDtos))
		{
			for(TransactionDTO transactionDto: transactionDtos)
			{
				transaction.add(mapTransactionDTOToTransaction(transactionDto));
				
			}
		}
		return transaction;
		
		
	}
	
	public List<TransactionDTO>  mapTransactionToTransactionDtos(List<Transaction> transactions)
	{

		List<TransactionDTO> transactionDto= new ArrayList<TransactionDTO>();
		
		if(!CollectionUtils.isEmpty(transactions))
		{
			for(Transaction transaction: transactions)
			{
				transactionDto.add(mapTransactionDTOToTransaction(transaction));
				
			}
		}
		return transactionDto;
		
		
	}

}
