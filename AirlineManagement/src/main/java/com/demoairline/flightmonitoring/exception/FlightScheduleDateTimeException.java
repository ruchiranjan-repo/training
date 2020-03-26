package com.demoairline.flightmonitoring.exception;

/**
 * Exception class to be used when flight Scheduled date and time should be in future
 * 
 * @author Guruchandru
 *
 */
import java.time.LocalDateTime;

public class FlightScheduleDateTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 669L;

	public FlightScheduleDateTimeException(LocalDateTime scheduleDateTime) {
		super("Scheduled date and time should be in future: " + scheduleDateTime);
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}
}
