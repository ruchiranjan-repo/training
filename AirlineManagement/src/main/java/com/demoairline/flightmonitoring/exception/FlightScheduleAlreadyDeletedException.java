package com.demoairline.flightmonitoring.exception;

/**
 * Exception class to be used when flight with the provided flight id not
 * available.
 * 
 * @author Ruchi
 *
 */
public class FlightScheduleAlreadyDeletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 661L;

	public FlightScheduleAlreadyDeletedException(Long scheduleId) {
		super("Scheduled flight with schedule Id " + scheduleId + "already deleted");

	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}

}
