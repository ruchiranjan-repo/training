package com.demoairline.flightmonitoring.dto;

import java.util.List;

import com.demoairline.flightmonitoring.dto.FlightDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightsResponse {

	@JsonProperty("flights")
	private List<FlightDto> flights;

	@JsonProperty("sizeOfFlightList")
	private Integer sizeOfFlightList;

	public FlightsResponse(List<FlightDto> flights, Integer sizeOfFlightList) {
		super();
		this.flights = flights;
		this.sizeOfFlightList = sizeOfFlightList;
	}

	public List<FlightDto> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightDto> flights) {
		this.flights = flights;
	}

	public Integer getSizeOfFlightList() {
		return sizeOfFlightList;
	}

	public void setSizeOfFlightList(Integer sizeOfFlightList) {
		this.sizeOfFlightList = sizeOfFlightList;
	}

	
}
