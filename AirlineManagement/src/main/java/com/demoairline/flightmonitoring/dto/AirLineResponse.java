package com.demoairline.flightmonitoring.dto;

/**
 * Response class for fetching airline details.
 * 
 * @author YaseenShaik
 */
import com.demoairline.flightmonitoring.entity.Airline;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirLineResponse {

	@JsonProperty("airline")
	private Airline airline;

	@JsonProperty("statusCode")
	private Integer statusCode = 656;

	public AirLineResponse(Airline airline, Integer statusCode) {
		super();
		this.airline = airline;
		this.statusCode = statusCode;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
