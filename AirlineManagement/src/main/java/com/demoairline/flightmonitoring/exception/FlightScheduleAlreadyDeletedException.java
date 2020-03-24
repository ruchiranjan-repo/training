package com.demoairline.flightmonitoring.exception;

public class FlightScheduleAlreadyDeletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 661L;

	public FlightScheduleAlreadyDeletedException(Long ScheduleId) {
		super("Scheduled flight with schedule Id " + ScheduleId + "already deleted");

	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}

}
