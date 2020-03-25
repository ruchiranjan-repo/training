package com.demoairline.flightmonitoring.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.demoairline.flightmonitoring.TestData;
import com.demoairline.flightmonitoring.entity.Airport;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.entity.FlightSchedule;
import com.demoairline.flightmonitoring.entity.Runway;

@DataJpaTest
public class FlightScheduleRepositoryTest {
	@Autowired 
	TestEntityManager testEntityManager;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	
	FlightSchedule flightSchedule;
	Airport airport;
	Runway runway;
	Flight flight;
	@BeforeEach
	public void setUp()
	{
		airport= new Airport();
		
		airport.setAirportCode(TestData.AIRPORT_CODE);
		airport.setAirportName(TestData.AIRPORT_NAME);
		airport.setAirportType(TestData.AIRPORT_TYPE);
		airport.setLocation(TestData.AIRPORT_NAME);
		airport.setNoOfRunways(10);
		
		runway= new Runway();
		
		runway.setRunway(TestData.RUNWAY_NAME);
		List<Runway> runways= new ArrayList<>();
		runways.add(runway);
		airport.setRunways(runways);
		
		flight = new Flight();
	
		flight.setFlightName(TestData.FLIGHT_NAME);
		flight.setFlightCode(TestData.FLIGHT_CODE);
		flight.setFlightStatus(TestData.FLIGHT_STATUS);
		flight.setFlightType("DOMESTIC");
		flight.setNoOfSeats(80);
		
		flightSchedule= new FlightSchedule();
		flightSchedule.setAirport(airport);
		flightSchedule.setFlight(flight);
		flightSchedule.setFlightCode(TestData.FLIGHT_CODE);
		flightSchedule.setRunwayID(TestData.RUNWAY_ID);
		flightSchedule.setScheduledDateTime(LocalDateTime.of(2007,12,03,00,00,00));
		flightSchedule.setScheduleType("ARRIVAL");
		flightSchedule.setScheduleStatus("SCHEDULED");
	}
	
	@Test
	public void testFindByScheduleId()
	{
		FlightSchedule persistedFlightSchedule= testEntityManager.persistAndFlush(flightSchedule);
		
		Optional<FlightSchedule> returnedFlightSchedule =flightScheduleRepository.findByScheduleId(persistedFlightSchedule.getScheduleId());
		
		assertThat(persistedFlightSchedule.getScheduleId()).isEqualTo(returnedFlightSchedule.get().getScheduleId());
		assertThat(persistedFlightSchedule.getFlightCode()).isEqualTo(returnedFlightSchedule.get().getFlightCode());
		assertThat(persistedFlightSchedule.getScheduledDateTime()).isEqualTo(returnedFlightSchedule.get().getScheduledDateTime());
		assertThat(persistedFlightSchedule.getRunwayID()).isEqualTo(returnedFlightSchedule.get().getRunwayID());
	}
	
	
	@Test
	public void testFindByScheduleIdNotFound()
	{
		testEntityManager.persistAndFlush(flightSchedule);
		
		Optional<FlightSchedule> returnedFlightSchedule =flightScheduleRepository.findByScheduleId(100L);
		
		assertFalse(returnedFlightSchedule.isPresent());
		
	}
	
	@Test
	public void testFindByRunwayIDAndScheduledDateTimeAndSchedule()
	{
		FlightSchedule persistedFlightSchedule= testEntityManager.persistAndFlush(flightSchedule);
		
		
		Optional<FlightSchedule> returnedFlightSchedule =flightScheduleRepository.findByRunwayIDAndScheduledDateTimeAndScheduleStatus(persistedFlightSchedule.getRunwayID(), persistedFlightSchedule.getScheduledDateTime(),"SCHEDULED");
		assertThat(persistedFlightSchedule.getScheduleId()).isEqualTo(returnedFlightSchedule.get().getScheduleId());
		assertThat(persistedFlightSchedule.getFlightCode()).isEqualTo(returnedFlightSchedule.get().getFlightCode());
		assertThat(persistedFlightSchedule.getScheduledDateTime()).isEqualTo(returnedFlightSchedule.get().getScheduledDateTime());
		assertThat(persistedFlightSchedule.getRunwayID()).isEqualTo(returnedFlightSchedule.get().getRunwayID());
	}
	
	@Test
	public void testFindByRunwayIDAndScheduledDateTimeNotFound()
	{
		FlightSchedule persistedFlightSchedule= testEntityManager.persistAndFlush(flightSchedule);
		
		Optional<FlightSchedule> returnedFlightSchedule =flightScheduleRepository.findByRunwayIDAndScheduledDateTimeAndScheduleStatus(persistedFlightSchedule.getRunwayID(), persistedFlightSchedule.getScheduledDateTime(),"CANCLED");
		
		assertFalse(returnedFlightSchedule.isPresent());
		
	}
	@Test
	public void testfindAllByFlightCode()
	{
		FlightSchedule persistedFlightSchedule= testEntityManager.persistAndFlush(flightSchedule);
		
		List<FlightSchedule> returnedFlightSchedules =flightScheduleRepository.findAllByFlightCode( persistedFlightSchedule.getFlightCode());
		assertFalse(CollectionUtils.isEmpty(returnedFlightSchedules));
		assertThat(returnedFlightSchedules.size()).isEqualTo(1);
	}
	@Test
	public void testfindAllByFlightCodeNotFound()
	{
		testEntityManager.persistAndFlush(flightSchedule);
		
		List<FlightSchedule> returnedFlightSchedules =flightScheduleRepository.findAllByFlightCode("AI001");
		assertTrue(CollectionUtils.isEmpty(returnedFlightSchedules));
		assertThat(returnedFlightSchedules.size()).isEqualTo(0);
	}
	

}
