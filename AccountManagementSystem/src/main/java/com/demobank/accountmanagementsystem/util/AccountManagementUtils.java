package com.demobank.accountmanagementsystem.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountManagementUtils {
	
	  static AtomicInteger accountNumberSeq = new AtomicInteger(1000000);
		
	 public static Long accountNumberGenerator()	{
					
		return (long) accountNumberSeq.incrementAndGet();
			
		}
	

}
