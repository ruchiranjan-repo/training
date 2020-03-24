package com.demoairline.flightmonitoring.dto;

import com.demoairline.flightmonitoring.entity.Flight;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightResponse {

	@JsonProperty("flight")
	private Flight flight;
	@JsonProperty("statusCode")
	private Integer statusCode = 666;

	public FlightResponse(Flight flight, Integer statusCode) {
		super();
		this.flight = flight;
		this.statusCode = statusCode;
	}

}
