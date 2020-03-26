package com.demoairline.flightmonitoring.exception;
/**
 * Exception class to be used when Airlines are not available.
 * 
 * @author YaseenShaik
 *
 */

public class AirLinesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Long ERROR_CODE = 661L;

	public AirLinesNotFoundException() {
		super("Airlines not found");
	}

	public Long getErrorCode() {
		return ERROR_CODE;
	}

}
