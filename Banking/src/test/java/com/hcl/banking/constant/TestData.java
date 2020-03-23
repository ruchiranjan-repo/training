package com.hcl.banking.constant;

import java.math.BigDecimal;
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
	public static final BigDecimal AMOUNT_TRANSAFERED=new BigDecimal(200);
	public static final Long ACCOUNT_NUMBER_1=1001L;
	public static final Long CUSTOMER_ID = 100101L;
	public static final Long ACCOUNT_ID_2 = 100102L;
	public static final Long ACCOUNT_ID_3 = 100103L;
	public static final Long TRANSACTION_REF_1 = 1L;
	public static final Long TRANSACTION_REF_2 = 2L;
	public static final Long TRANSACTION_REF_3 = 3L;
	public static final Long TRANSACTION_REF_4 = 4L;
}
