package com.demoairline.flightmonitoring.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.exception.AirLineNotFoundException;
import com.demoairline.flightmonitoring.exception.AirLinesNotFoundException;
import com.demoairline.flightmonitoring.repositories.AirlineRepository;
import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	public AirLinesResponse getAirlines() {
		
		

		List<Airline> airlines = airlineRepository.findAll();
		if (airlines.isEmpty()) {
			throw new AirLinesNotFoundException();
		}
		AirLinesResponse airLinesResponse = new AirLinesResponse(airlines, airlines.size());

		return airLinesResponse;
	}

	public AirLineResponse getAirlinesByAirlinId(Long airlineId) {

		Optional<Airline> airline = airlineRepository.findById(airlineId);
		if (!airline.isPresent()) {
			throw new AirLineNotFoundException(airlineId);
		}
		AirLineResponse airLineResponse = new AirLineResponse(airline.get(), 661);

		return airLineResponse;
	}

}
