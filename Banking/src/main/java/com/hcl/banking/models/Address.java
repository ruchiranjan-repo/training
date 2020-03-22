package com.hcl.banking.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.hcl.banking.constants.AddressEnum;

@Embeddable
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;

	String addressStreet;
	
	 String addressCity;
	 
	 String addressPin;
	 
	 AddressEnum  addressType;

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressPin() {
		return addressPin;
	}

	public void setAddressPin(String addressPin) {
		this.addressPin = addressPin;
	}

	public AddressEnum getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressEnum addressType) {
		this.addressType = addressType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 
	 
	

}
