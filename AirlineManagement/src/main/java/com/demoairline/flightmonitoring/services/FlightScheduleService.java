package com.demoairline.flightmonitoring.services;
/**
 * Interface for Flight schedule service.
 * @author Ruchi
 */

import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;

public interface FlightScheduleService {
	
	public MessageResponseDto addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO);
	public MessageResponseDto updateFlightSchedule(FlightScheduleUpdateRequest flightScheduleRequestDTO);

	

}
