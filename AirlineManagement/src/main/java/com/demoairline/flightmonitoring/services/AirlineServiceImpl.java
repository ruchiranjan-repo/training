package com.demoairline.flightmonitoring.services;

/**
 * Implementation for Airline service implementation.
 * @author YaseenShaik
 */
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.exception.AirLineNotFoundException;
import com.demoairline.flightmonitoring.exception.AirLinesNotFoundException;
import com.demoairline.flightmonitoring.repositories.AirlineRepository;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	/**
	 * Method is used to fetch the airline by using airline id.
	 * 
	 * @throws AirLineNotFoundException
	 */
	public AirLinesResponse getAirlines() {

		List<Airline> airlines = airlineRepository.findAll();
		if (airlines.isEmpty()) {
			throw new AirLinesNotFoundException();
		}

		return new AirLinesResponse(airlines, airlines.size());
	}

	/**
	 * Method is used to fetch the airlines.
	 * 
	 * @throws AirLinesNotFoundException
	 */
	public AirLineResponse getAirlinesByAirlinId(Long airlineId) {

		Optional<Airline> airline = airlineRepository.findById(airlineId);
		if (!airline.isPresent()) {
			throw new AirLineNotFoundException(airlineId);
		}

		return new AirLineResponse(airline.get(), 661);
	}

}
