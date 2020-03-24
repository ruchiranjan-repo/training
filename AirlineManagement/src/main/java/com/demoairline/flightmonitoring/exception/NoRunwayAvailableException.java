package com.demoairline.flightmonitoring.exception;

/**
 * Exception  to be used when no runway is available to schedule the flights arrival or takeoff.
 * @author Ruchi
 *
 */
public class NoRunwayAvailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 603L;

	public NoRunwayAvailableException(Long flightId, Long airportId) {
		super("No runway available to schedule the takoff or landing of the flight "+flightId+" on airport  "+airportId);
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
}
