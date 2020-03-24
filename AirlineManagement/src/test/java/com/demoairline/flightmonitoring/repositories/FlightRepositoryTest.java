package com.demoairline.flightmonitoring.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.entity.Flight;



@DataJpaTest
public class FlightRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	private FlightRepository flightRepository;

	Flight flight;

	Airline airline;

	@BeforeEach
	public void setUp() {

		flight = new Flight();
		airline = new Airline();
		airline.setAirlineName("Indigo");
		airline.setAirlineStatus("available");
		airline.setRegistrationNumber("123");
		flight.setAirline(airline);
		flight.setFlightCode("111");
		flight.setFlightName("Indi");
		flight.setNoOfSeats(100);
		flight.setFlightStatus("AVAILABLE");
		flight.setFlightType("ABC");

	}

	@Test
	public void flightsNotFoundExceptionTest() {
		testEntityManager.persistAndFlush(airline);
		testEntityManager.persistAndFlush(flight);

		List<Flight> checkFlight = flightRepository.findAll();

		assertFalse(CollectionUtils.isEmpty(checkFlight));

	}

	@Test
	public void flightNotFoundExceptionTest() {
		testEntityManager.persist(flight);

		Optional<Flight> checkFlight = flightRepository.findById(10l);

		assertFalse(checkFlight.isPresent());
	}

}
