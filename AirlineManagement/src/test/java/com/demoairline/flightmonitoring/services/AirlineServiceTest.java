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

import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.exception.AirLineNotFoundException;
import com.demoairline.flightmonitoring.exception.AirLinesNotFoundException;
import com.demoairline.flightmonitoring.repositories.AirlineRepository;

@SpringBootTest
public class AirlineServiceTest {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private AirlineServiceImpl airlineServiceImpl;

	@MockBean
	private AirlineRepository airlineRepository;

	Airline airline, airline1;

	@BeforeEach
	public void setUp() {

		airline = new Airline();
		airline.setAirlineId(10l);
		airline.setAirlineName("Indigo");
		airline.setAirlineStatus("available");
		airline.setRegistrationNumber("123");
		airline1 = new Airline();
		airline1.setAirlineId(20l);
		airline1.setAirlineName("Spycejet");
		airline1.setAirlineStatus("available");
		airline1.setRegistrationNumber("321");

	}
	/**
	 * Method is used to test fetching the airline by using airline id.
	 * 
	 * @throws AirLineNotFoundException
	 */
	@Test
	public void getAirlineByAirlineIdTest() {

		when(airlineRepository.findById(10L)).thenReturn(Optional.of(airline));

		AirLineResponse airLineResponse = airlineService.getAirlinesByAirlinId(10l);

		AirLineResponse expectedairLineResponse = new AirLineResponse(airline, 661);

		assertEquals(expectedairLineResponse.getAirline(), airLineResponse.getAirline());

		assertEquals(expectedairLineResponse.getStatusCode(), airLineResponse.getStatusCode());

	}

	/**
	 * Method is used to test fetch the airlines.
	 * 
	 * @throws AirLinesNotFoundException
	 */
	@Test
	public void getAllAirLinesTest() {

		List<Airline> airlines = new ArrayList<Airline>();
		airlines.add(airline);
		airlines.add(airline1);

		when(airlineRepository.findAll()).thenReturn(airlines);

		AirLinesResponse airLineResponse = airlineServiceImpl.getAirlines();

		AirLinesResponse airLinesResponse = new AirLinesResponse(airlines, airlines.size());

		assertEquals(airLineResponse.getAirlines(), airLinesResponse.getAirlines());

	}
	/**
	 * Method is used to test if the airline not found.
	 * 
	 * @throws AirLineNotFoundException
	 */
	@Test
	public void testAirlineNotFoundException() {

		Mockito.when(airlineRepository.findById(1l)).thenReturn(Optional.empty());
		assertThrows(AirLineNotFoundException.class, () -> {
			airlineServiceImpl.getAirlinesByAirlinId(1l);
		});
	}
	/**
	 * Method is used to test if the airlines not found.
	 * 
	 * @throws AirLinesNotFoundException
	 */
	@Test
	public void testAirlinesNotFoundException() {
		List<Airline> airlines = new ArrayList<Airline>();

		Mockito.when(airlineRepository.findAll()).thenReturn(airlines);
		assertThrows(AirLinesNotFoundException.class, () -> {
			airlineServiceImpl.getAirlines();
		});
	}

}
