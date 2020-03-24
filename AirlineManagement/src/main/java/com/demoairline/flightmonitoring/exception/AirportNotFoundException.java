package com.demoairline.flightmonitoring.exception;
/**
 * Exception class to be used when airport with the provided airport id not available.
 * @author Ruchi
 *
 */


public class AirportNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 602L;

	public AirportNotFoundException(Long airportId) {
		super("Airport with airport id " + airportId + "does not exists.");
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
}
