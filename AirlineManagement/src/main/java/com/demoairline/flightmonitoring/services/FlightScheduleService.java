package com.demoairline.flightmonitoring.services;

/**
 * Interface for Flight schedule service.
 * @author Ruchi
 */

import java.util.List;

import com.demoairline.flightmonitoring.dto.CancelScheduleDto;
import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;
import com.demoairline.flightmonitoring.dto.ScheduleResponseDto;

public interface FlightScheduleService {

	public MessageResponseDto addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO);

	public MessageResponseDto updateFlightSchedule(FlightScheduleUpdateRequest flightScheduleRequestDTO);

	public CancelScheduleDto cancelScheduleByScheduleId(Long ScheduleId);

	public List<ScheduleResponseDto> getFlightScheduleByFlightCode(String flightCode);

}
