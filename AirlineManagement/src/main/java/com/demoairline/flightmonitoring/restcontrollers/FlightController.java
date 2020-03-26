package com.demoairline.flightmonitoring.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demoairline.flightmonitoring.dto.FlightResponse;
import com.demoairline.flightmonitoring.dto.FlightsResponse;
import com.demoairline.flightmonitoring.exception.FlightNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightsNotFoundException;
import com.demoairline.flightmonitoring.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	private Logger logger = LoggerFactory.getLogger(FlightController.class);

	/**
	 * 
	 *
	 * @param flightId  flightId
	 * @return flight details through flight Id
	 */
	@GetMapping("/{flightId}")
	public ResponseEntity<FlightResponse> getFlightByFlightId(@PathVariable("flightId") Long flightId) {

		logger.info("Fetching flight details");

		return new ResponseEntity<>(flightService.getFlightByFlightId(flightId), HttpStatus.OK);
	}
	/**
	 * 
	 * @param pageSize
	 * @param pageNumber
	 * @return all flights
	 * @throws FlightsNotFoundException
	 */

	@GetMapping("")
	public ResponseEntity<FlightsResponse> getFlights() {

		logger.info("Fetching airlines details");

		return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
	}



}
