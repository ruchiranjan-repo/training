package com.demoairline.flightmonitoring.services;
/**
 * Interface for Airline  service.
 * @author YaseenShaik
 */

import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;

public interface AirlineService {

	AirLinesResponse getAirlines();

	AirLineResponse getAirlinesByAirlinId(Long airlineId);

}
