package com.demoairline.flightmonitoring.exception;
/**
 * Exception class to be used when flight  with the provided flight id not available.
 * @author Ruchi
 *
 */

public class FlightNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 601L;

	public FlightNotFoundException(Long flightId) {
		super("Flight with flight id " + flightId + "does not exists.");
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
}
