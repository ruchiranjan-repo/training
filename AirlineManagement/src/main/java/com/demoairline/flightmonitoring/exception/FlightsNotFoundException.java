package com.demoairline.flightmonitoring.exception;
/**
 * Exception class to be used when Flights are not available.
 * 
 * @author YaseenShaik
 *
 */
public class FlightsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 669L;

	public FlightsNotFoundException() {
		super("Flights not found");
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}

}
