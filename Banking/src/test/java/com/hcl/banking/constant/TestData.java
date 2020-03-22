package com.hcl.banking.constant;

import java.time.LocalDate;

import com.hcl.banking.constants.AddressEnum;

public class TestData {
	public static final String CUSTOMER_NAME = "TestCustomer";
	public static final String CUSTOMER_EMAIL_ID = "TestCustomer@gmail.com";
	public static final LocalDate CUSTOMER_DOB = LocalDate.of(1990, 12, 20);
	public static final String CUSTOMER_CONTACT = "9731087744";
	public static final String CUSTOMER_ADDRESS_CITY = "Bangalore";
	public static final String CUSTOMER_ADDRESS_PIN = "560100";
	public static final String CUSTOMER_ADDRESS_STREET = "Doddathogur";
	public static final AddressEnum ADRESS_TYPE = AddressEnum.MAILING_ADDRESS;
	public static final Long ACCOUNT_NUMBER=1000L;
	public static final String IFSC_CODE="IFSC001" ;
	public static final String IFSC_CODE_NOT_FOUND="IFSC002" ;
}
