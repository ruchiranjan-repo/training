package com.demoairline.flightmonitoring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demoairline.flightmonitoring.dto.FlightResponse;
import com.demoairline.flightmonitoring.dto.FlightsResponse;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.exception.FlightNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightsNotFoundException;
import com.demoairline.flightmonitoring.repositories.FlightRepository;

@SpringBootTest
public class FlightServiceTest {

	@Autowired
	private FlightService airlineService;

	@Autowired
	private FlightServiceImpl flightServiceImpl;

	@MockBean
	private FlightRepository flightRepository;

	Flight flight;

	Airline airline;

	@BeforeEach
	public void setUp() {

		flight = new Flight();
		flight.setFlightId(10l);
		airline = new Airline();
		airline.setAirlineId(10l);
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

	/**
	 * Method is used to test by fetch the flight by using flight id.
	 * 
	 * @throws FlightNotFoundException
	 */
	@Test
	public void getAirlineByAirlineIdTest() {

		when(flightRepository.findById(10L)).thenReturn(Optional.of(flight));

		FlightResponse flightResponse = airlineService.getFlightByFlightId(10l);

		FlightResponse expectedflightResponse = new FlightResponse(flight, 666);

		assertEquals(expectedflightResponse.getFlight(), flightResponse.getFlight());

		assertEquals(expectedflightResponse.getStatusCode(), flightResponse.getStatusCode());

	}

	/**
	 * Method is used to test by fetch the flights.
	 * 
	 * @throws FlightsNotFoundException
	 */
	@Test
	public void getAllFlightsTest() {

		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		flights.add(flight);

		when(flightRepository.findAll()).thenReturn(flights);

		FlightsResponse flightResponse = flightServiceImpl.getFlights();

		assertEquals(2, flightResponse.getFlights().size());

	}

	/**
	 * Method is used to test by flights not found.
	 * 
	 * @throws FlightsNotFoundException
	 */
	@Test
	public void TestFlightNotFoundException() {

		Mockito.when(flightRepository.findByFlightId(1l)).thenReturn(Optional.empty());
		assertThrows(FlightNotFoundException.class, () -> {
			flightServiceImpl.getFlightByFlightId(1l);
		});
	}

	/**
	 * Method is used to test flight not found.
	 * 
	 * @throws FlightNotFoundException
	 */
	@Test
	public void TestFlightsNotFoundException() {
		List<Flight> flights = new ArrayList<Flight>();

		Mockito.when(flightRepository.findAll()).thenReturn(flights);
		assertThrows(FlightsNotFoundException.class, () -> {
			flightServiceImpl.getFlights();
		});
	}

}
