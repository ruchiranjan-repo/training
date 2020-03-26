package com.demoairline.flightmonitoring.exception;
/**
 * Exception class to be used when Airline with the provided Airline id not
 * available.
 * 
 * @author YaseenShaik
 *
 */
public class AirLineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 661L;

	public AirLineNotFoundException(Long airlineId) {
		super("Airline not found with an airline id: " + airlineId);
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}
}
