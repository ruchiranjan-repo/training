package com.demoairline.flightmonitoring.exception;
/**
 * Exception  to be used when flight schedule not available..
 * @author Ruchi
 *
 */

public class FlightScheduleNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 604L;

	public FlightScheduleNotFoundException(Long scheduleId) {
		super("No flight schedule found with schedule id" + scheduleId );
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
}
