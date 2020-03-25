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

import com.demoairline.flightmonitoring.exception.AirLinesNotFoundException;
import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;
import com.demoairline.flightmonitoring.services.AirlineService;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	private Logger logger = LoggerFactory.getLogger(AirlineController.class);

	/**
	 * 
	 * 
	 * @return All airlines details
	 */
	@GetMapping("")
	public ResponseEntity<AirLinesResponse> getAirlines() {

		logger.info("Fetching airlines details");

		return new ResponseEntity<>(airlineService.getAirlines(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param airlineId
	 * @return Airline details followed by
	 */
	@GetMapping("/{airlineId}")
	public ResponseEntity<AirLineResponse> getAirlinesByAirlinId(@PathVariable("airlineId") Long airlineId) {

		logger.info("Fetching airline details");

		return new ResponseEntity<>(airlineService.getAirlinesByAirlinId(airlineId), HttpStatus.OK);
	}

}
