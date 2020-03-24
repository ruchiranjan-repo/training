package com.demoairline.flightmonitoring.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.demoairline.flightmonitoring.dto.FlightDto;
import com.demoairline.flightmonitoring.dto.FlightResponse;
import com.demoairline.flightmonitoring.dto.FlightsResponse;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.services.FlightService;



@SpringBootTest
public class FlightControllerTest {

	@MockBean
	private FlightService flightService;

	@Autowired
	private FlightController flightController;

	Flight flight;

	Airline airline;

	@BeforeEach
	public void setUp() {

		flight = new Flight();
		flight.setFlightId(10l);
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
	public void getAirlineByAirlineIdTest() {
		FlightResponse flightResponse = new FlightResponse(flight, 767);

		Mockito.when(flightService.getFlightByFlightId(10l)).thenReturn(flightResponse);

		ResponseEntity<FlightResponse> expectflightResponse = flightController.getFlightByFlightId(10l);

		assertEquals(flightResponse, expectflightResponse.getBody());

	}

	@Test
	public void getAirlinesByAirlineIdTest() {
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);

		List<FlightDto> flightDtos = flights.stream().map(flight -> {
			FlightDto flightDto = new FlightDto();
			BeanUtils.copyProperties(flight, flightDto);
			return flightDto;
		}).collect(Collectors.toList());

		FlightsResponse flightsResponse = new FlightsResponse(flightDtos, 677);

		Mockito.when(flightService.getFlights()).thenReturn(flightsResponse);

		ResponseEntity<FlightsResponse> responseEntity = flightController.getFlights();

		assertEquals(flightsResponse, responseEntity.getBody());

	}

}
