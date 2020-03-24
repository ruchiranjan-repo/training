package com.demoairline.flightmonitoring.exception;

/**
 * Exception  to be used when runway with provided Id is not associated with airport..
 * @author Ruchi
 *
 */
public class AirportRunwayNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Long ERROR_CODE = 607L;

	public AirportRunwayNotFoundException(Long runwayId, Long airportId) {
		super("Runway with runwayId "+runwayId+" is not available on airport id "+airportId);
	}
	
	public Long getErrorCode()
	{
		return ERROR_CODE;
	}
}
