package com.demoairline.flightmonitoring.services;

/**
 * Implementation for Flight service Implementation.
 * @author YaseenShaik
 */
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoairline.flightmonitoring.dto.FlightDto;
import com.demoairline.flightmonitoring.dto.FlightResponse;
import com.demoairline.flightmonitoring.dto.FlightsResponse;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.exception.FlightNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightsNotFoundException;
import com.demoairline.flightmonitoring.repositories.FlightRepository;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	/**
	 * Method is used to fetch the flight by using flight id.
	 * 
	 * @throws FlightNotFoundException
	 */
	public FlightResponse getFlightByFlightId(Long flightId) {

		Optional<Flight> flight = flightRepository.findById(flightId);

		if (!flight.isPresent()) {
			throw new FlightNotFoundException(flightId);
		}

		return new FlightResponse(flight.get(), 666);
	}

	/**
	 * Method is used to fetch the flights.
	 * 
	 * @throws FlightsNotFoundException
	 */
	public FlightsResponse getFlights() {
		List<Flight> flights = flightRepository.findAll();

		if (flights.isEmpty()) {
			throw new FlightsNotFoundException();
		}

		List<FlightDto> flightDtos = flights.stream().map(flight -> {
			FlightDto flightDto = new FlightDto();
			BeanUtils.copyProperties(flight, flightDto);
			return flightDto;
		}).collect(Collectors.toList());

		return new FlightsResponse(flightDtos, flights.size());
	}

}
