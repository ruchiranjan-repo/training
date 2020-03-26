package com.demoairline.flightmonitoring.dto;

/**
 * Response class for fetching airlines details.
 * 
 * @author YaseenShaik
 */
import java.util.List;

import com.demoairline.flightmonitoring.entity.Airline;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirLinesResponse {

	@JsonProperty("airlines")
	private List<Airline> airlines;
	
	@JsonProperty("sizeOfAirlines")
	private Integer sizeOfAirlines;

	public AirLinesResponse(List<Airline> airlines, Integer sizeOfAirlines) {
		super();
		this.airlines = airlines;
		this.sizeOfAirlines = sizeOfAirlines;
	}

	public List<Airline> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}

	public Integer getSizeOfAirlines() {
		return sizeOfAirlines;
	}

	public void setSizeOfAirlines(Integer sizeOfAirlines) {
		this.sizeOfAirlines = sizeOfAirlines;
	}
	
	

}
