package com.demoairline.flightmonitoring.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.demoairline.flightmonitoring.dto.AirLineResponse;
import com.demoairline.flightmonitoring.dto.AirLinesResponse;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.services.AirlineServiceImpl;

@SpringBootTest
public class AirlineControllerTest {

	@MockBean
	private AirlineServiceImpl airlineService;

	@Autowired
	private AirlineController airlineController;

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

	@Test
	public void getAirlineByAirlineIdTest() {
		AirLineResponse airLineResponse = new AirLineResponse(airline, 767);

		Mockito.when(airlineService.getAirlinesByAirlinId(10l)).thenReturn(airLineResponse);

		ResponseEntity<AirLineResponse> responseEntity = airlineController.getAirlinesByAirlinId(10l);

		assertEquals(airLineResponse, responseEntity.getBody());

	}

	@Test
	public void getAirlinesByAirlineIdTest() {
		List<Airline> airlines = new ArrayList<Airline>();
		airlines.add(airline);
		airlines.add(airline1);

		AirLinesResponse airLineResponse = new AirLinesResponse(airlines, 677);

		Mockito.when(airlineService.getAirlines()).thenReturn(airLineResponse);

		ResponseEntity<AirLinesResponse> responseEntity = airlineController.getAirlines();

		assertEquals(airLineResponse, responseEntity.getBody());

	}

}
