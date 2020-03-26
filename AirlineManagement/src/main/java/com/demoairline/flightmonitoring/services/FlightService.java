package com.demoairline.flightmonitoring.services;

/**
 * Interface for Flight  service.
 * @author YaseenShaik
 */
import com.demoairline.flightmonitoring.dto.FlightResponse;
import com.demoairline.flightmonitoring.dto.FlightsResponse;

public interface FlightService {

	FlightResponse getFlightByFlightId(Long flightId);

	FlightsResponse getFlights();

}
