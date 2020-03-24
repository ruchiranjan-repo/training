package com.demoairline.flightmonitoring.restcontrollers;
/**
 * Restcontroller class for flight schedule.
 * 
 * @Author Ruchi
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;
import com.demoairline.flightmonitoring.services.FlightScheduleService;

@RestController
@RequestMapping(value="/flightschedule")
public class FlightScheduleRestController {
	
	@Autowired 
	FlightScheduleService flightScheduleService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addFlightSchedule( @Valid @RequestBody FlightScheduleRequestDTO flightScheduleRequestDTO)
	{
		MessageResponseDto messageResponseDto= flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		return new ResponseEntity<>(messageResponseDto,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> updateFlightSchedule( @Valid @RequestBody FlightScheduleUpdateRequest flightScheduleUpdateRequest)
	{
		MessageResponseDto messageResponseDto= flightScheduleService.updateFlightSchedule(flightScheduleUpdateRequest);
		return new ResponseEntity<>(messageResponseDto,HttpStatus.OK);
	}

}
