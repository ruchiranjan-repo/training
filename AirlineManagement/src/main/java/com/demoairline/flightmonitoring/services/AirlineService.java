package com.demoairline.flightmonitoring.services;

import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;

public interface AirlineService {

	AirLinesResponse getAirlines();

	AirLineResponse getAirlinesByAirlinId(Long airlineId);

}
