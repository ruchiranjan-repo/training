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



@DataJpaTest
public class AirlineRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	private AirlineRepository airlineRepository;

	Airline airline, airline1;

	@BeforeEach
	public void setUp() {

		airline = new Airline();
		airline.setAirlineName("Indigo");
		airline.setAirlineStatus("available");
		airline.setRegistrationNumber("123");
		airline1 = new Airline();
		airline1.setAirlineName("Spycejet");
		airline1.setAirlineStatus("available");
		airline1.setRegistrationNumber("321");

	}

	@Test
	public void airlinesNotFoundExceptionTest() {
		testEntityManager.persistAndFlush(airline);
		testEntityManager.persistAndFlush(airline1);

		List<Airline> checkAirline = airlineRepository.findAll();

		assertFalse(CollectionUtils.isEmpty(checkAirline));

	}

	@Test
	public void airlineNotFoundExceptionTest() {
		testEntityManager.persistAndFlush(airline);

		Optional<Airline> checkAirline = airlineRepository.findById(10l);

		assertFalse(checkAirline.isPresent());

	}
}
