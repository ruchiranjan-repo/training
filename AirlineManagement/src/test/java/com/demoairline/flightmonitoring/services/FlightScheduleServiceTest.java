package com.demoairline.flightmonitoring.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demoairline.flightmonitoring.TestData;
import com.demoairline.flightmonitoring.dto.CancelScheduleDto;
import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;
import com.demoairline.flightmonitoring.dto.ScheduleResponseDto;
import com.demoairline.flightmonitoring.entity.Airline;
import com.demoairline.flightmonitoring.entity.Airport;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.entity.FlightSchedule;
import com.demoairline.flightmonitoring.entity.Runway;
import com.demoairline.flightmonitoring.exception.AirportNotFoundException;
import com.demoairline.flightmonitoring.exception.AirportRunwayNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightScheduleDateTimeException;
import com.demoairline.flightmonitoring.exception.FlightScheduleNotFoundException;
import com.demoairline.flightmonitoring.exception.NoRunwayAvailableException;
import com.demoairline.flightmonitoring.repositories.Airportrepository;
import com.demoairline.flightmonitoring.repositories.FlightRepository;
import com.demoairline.flightmonitoring.repositories.FlightScheduleRepository;
import com.demoairline.flightmonitoring.repositories.RunwayRepository;

@SpringBootTest
public class FlightScheduleServiceTest {

	@Autowired
	FlightScheduleService flightScheduleService;
	@Autowired
	FlightScheduleServiceImpl flightScheduleServiceImpl;

	@MockBean
	FlightScheduleRepository flightScheduleRepository;
	@MockBean
	FlightRepository flightRepository;
	@MockBean
	Airportrepository airportRepository;
	
	@MockBean
	RunwayRepository runwayRepository;

	FlightSchedule flightSchedule;
	Airport airport;
	Runway runway;
	Flight flight;
	public static final LocalDateTime FUTURE_DATE_TIME = LocalDateTime.of(2021, 12, 03, 00, 00, 00);
	public static final LocalDateTime PAST_DATE_TIME = LocalDateTime.of(2019, 12, 03, 00, 00, 00);
	public static final Long RUNWAY_ID_NOT_FOUND = 10011L;

	@BeforeEach
	public void setUp() {
		airport = new Airport();
		airport.setAirportId(TestData.AIRPORT_ID);
		airport.setAirportCode(TestData.AIRPORT_CODE);
		airport.setAirportName(TestData.AIRPORT_NAME);
		airport.setAirportType(TestData.AIRPORT_TYPE);
		airport.setLocation(TestData.AIRPORT_NAME);
		airport.setNoOfRunways(10);

		runway = new Runway();
		runway.setRunwayID(TestData.RUNWAY_ID);
		runway.setRunwayNumber(TestData.RUNWAY_NAME);
		List<Runway> runways = new ArrayList<>();
		runways.add(runway);
		airport.setRunways(runways);

		flight = new Flight();
		flight.setFlightId(TestData.FLIGHT_ID);
		flight.setFlightName(TestData.FLIGHT_NAME);
		flight.setFlightCode(TestData.FLIGHT_CODE);
		flight.setFlightStatus(TestData.FLIGHT_STATUS);
		flight.setFlightType("DOMESTIC");
		flight.setNoOfSeats(80);

		flightSchedule = new FlightSchedule();
		flightSchedule.setScheduleId(1000L);
		flightSchedule.setAirport(airport);
		flightSchedule.setFlight(flight);
		flightSchedule.setFlightCode(TestData.FLIGHT_CODE);
		flightSchedule.setRunwayID(TestData.RUNWAY_ID);
		flightSchedule.setScheduledDateTime(LocalDateTime.of(2007, 12, 03, 00, 00, 00));
		flightSchedule.setScheduleType("ARRIVAL");
	}

	@Test
	public void testAddFlightSchedule() {

		when(flightRepository.findByFlightId(TestData.FLIGHT_ID)).thenReturn(Optional.of(flight));

		when(airportRepository.findByAirportId(TestData.AIRPORT_ID)).thenReturn(Optional.of(airport));

		when(flightScheduleRepository.findByRunwayIDAndScheduledDateTimeAndScheduleStatus(TestData.RUNWAY_ID,
				FUTURE_DATE_TIME, "SCHEDULED")).thenReturn(Optional.empty());

		when(flightScheduleRepository.save(flightSchedule)).thenReturn(flightSchedule);
		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setAirportId(TestData.AIRPORT_ID);
		flightScheduleRequestDTO.setFlightId(TestData.FLIGHT_ID);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);
		flightScheduleRequestDTO.setScheduleType("ARRIVAL");
		MessageResponseDto messageResponseDto = flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		assertThat(messageResponseDto.getMessage()).isEqualTo("Flight Scheduled  successfully");

		assertThat(messageResponseDto.getStatusCode()).isEqualTo(620L);
	}

	@Test
	public void testAddFlightScheduleThrowsFlightScheduleDateTimeException() {

		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setAirportId(TestData.AIRPORT_ID);
		flightScheduleRequestDTO.setFlightId(TestData.FLIGHT_ID);
		flightScheduleRequestDTO.setScheduledDateTime(PAST_DATE_TIME);
		flightScheduleRequestDTO.setScheduleType("ARRIVAL");
		assertThrows(FlightScheduleDateTimeException.class, () -> {
			flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		});
	}

	@Test
	public void testAddFlightScheduleThrowsFlightNotFoundException() {
		when(flightRepository.findByFlightId(TestData.FLIGHT_ID)).thenReturn(Optional.empty());

		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setAirportId(TestData.AIRPORT_ID);
		flightScheduleRequestDTO.setFlightId(TestData.FLIGHT_ID);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);
		flightScheduleRequestDTO.setScheduleType("ARRIVAL");
		assertThrows(FlightNotFoundException.class, () -> {
			flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		});
	}

	@Test
	public void testAddFlightScheduleThrowsAirportNotFoundException() {
		when(flightRepository.findByFlightId(TestData.FLIGHT_ID)).thenReturn(Optional.of(flight));

		when(airportRepository.findByAirportId(TestData.AIRPORT_ID)).thenReturn(Optional.empty());

		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setAirportId(TestData.AIRPORT_ID);
		flightScheduleRequestDTO.setFlightId(TestData.FLIGHT_ID);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);
		flightScheduleRequestDTO.setScheduleType("ARRIVAL");
		assertThrows(AirportNotFoundException.class, () -> {
			flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		});
	}

	@Test
	public void testAddFlightScheduleThrowsNoRunwayAvailableException() {
		airport.setRunways(new ArrayList<Runway>());
		when(flightRepository.findByFlightId(TestData.FLIGHT_ID)).thenReturn(Optional.of(flight));

		when(airportRepository.findByAirportId(TestData.AIRPORT_ID)).thenReturn(Optional.of(airport));

		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setAirportId(TestData.AIRPORT_ID);
		flightScheduleRequestDTO.setFlightId(TestData.FLIGHT_ID);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);
		flightScheduleRequestDTO.setScheduleType("ARRIVAL");
		assertThrows(NoRunwayAvailableException.class, () -> {
			flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
		});
	}

	@Test
	public void testUpdateFlightSchedule() {

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.of(flightSchedule));

		when(flightScheduleRepository.findByRunwayIDAndScheduledDateTimeAndScheduleStatus(TestData.RUNWAY_ID,
				FUTURE_DATE_TIME, "SCHEDULED")).thenReturn(Optional.empty());

		when(flightScheduleRepository.save(flightSchedule)).thenReturn(flightSchedule);

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(TestData.RUNWAY_ID);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);
		MessageResponseDto messageResponseDto = flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		assertThat(messageResponseDto.getMessage()).isEqualTo("Flight Scheduled updated successfully");

		assertThat(messageResponseDto.getStatusCode()).isEqualTo(628L);
	}

	@Test
	public void testUpdateFlightScheduleThrowsFlightScheduleNotFoundException() {

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.empty());

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(TestData.RUNWAY_ID);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);

		assertThrows(FlightScheduleNotFoundException.class, () -> {
			flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		});

	}

	@Test
	public void testUpdateFlightScheduleThrowsFlightScheduleDateTimeException() {

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.of(flightSchedule));

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(TestData.RUNWAY_ID);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(PAST_DATE_TIME);

		assertThrows(FlightScheduleDateTimeException.class, () -> {
			flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		});

	}

	@Test
	public void testUpdateFlightScheduleThrowsAirportRunwayNotFoundException() {
		flightSchedule.getAirport().setRunways(new ArrayList<Runway>());

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.of(flightSchedule));

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(TestData.RUNWAY_ID);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);

		assertThrows(AirportRunwayNotFoundException.class, () -> {
			flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		});

	}

	@Test
	public void testUpdateFlightScheduleThrowsAirportRunwayNotFound() {

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.of(flightSchedule));

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(RUNWAY_ID_NOT_FOUND);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);

		assertThrows(AirportRunwayNotFoundException.class, () -> {
			flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		});

	}

	@Test
	public void testUpdateFlightScheduleThrowsNoRunwayAvailableException() {

		when(flightScheduleRepository.findByScheduleId(1000L)).thenReturn(Optional.of(flightSchedule));

		when(flightScheduleRepository.findByRunwayIDAndScheduledDateTimeAndScheduleStatus(TestData.RUNWAY_ID,
				FUTURE_DATE_TIME, "SCHEDULED")).thenReturn(Optional.of(flightSchedule));

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(TestData.RUNWAY_ID);
		flightScheduleRequestDTO.setScheduleId(1000L);
		flightScheduleRequestDTO.setScheduledDateTime(FUTURE_DATE_TIME);

		assertThrows(NoRunwayAvailableException.class, () -> {
			flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO);
		});

	}

	@Test
	public void getFlightSchedule() {

		List<FlightSchedule> flightSchedules = new ArrayList<FlightSchedule>();
		flightSchedules.add(flightSchedule);

		when(flightScheduleRepository.findAllByFlightCode(TestData.FLIGHT_CODE)).thenReturn(flightSchedules);

		List<ScheduleResponseDto> scheduleResponseDto = flightScheduleService
				.getFlightScheduleByFlightCode(TestData.FLIGHT_CODE);

		assertEquals(1, scheduleResponseDto.size());

	}

	@Test
	public void getFlightScheduleNotFoundTest() {

		List<FlightSchedule> flightSchedules = new ArrayList<FlightSchedule>();

		when(flightScheduleRepository.findAllByFlightCode(TestData.FLIGHT_CODE)).thenReturn(flightSchedules);

		assertThrows(FlightScheduleNotFoundException.class, () -> {
			flightScheduleService.getFlightScheduleByFlightCode(TestData.FLIGHT_CODE);
		});

	}
	
	@Test	
	public void testFlightScheduleCancel() 
	{
		FlightSchedule schedule=new FlightSchedule();
		schedule.setScheduleId(1000L);
		schedule.setRunwayID(1234L);
		schedule.setScheduleStatus("available");
		schedule.setScheduleType("Arrival");
		Flight flight=new Flight();
		flight.setFlightId(1L);
		flight.setFlightName("abc");
		Airline airline=new Airline();
		airline.setAirlineId(1L);
		schedule.setRunwayID(1L);
		Runway runway=new Runway();
		runway.setRunwayID(1L);
		Airport airport=new Airport();
		airport.setAirportId(1L);
		Mockito.when(flightScheduleRepository.findById(1000L)).thenReturn(Optional.of(schedule));
		Mockito.when(runwayRepository.findById(1L)).thenReturn(Optional.of(runway));
		CancelScheduleDto response= flightScheduleServiceImpl.cancelScheduleByScheduleId(1000L);
		assertEquals("The Schedule flight is being canceled", response.getMeassge());
		
		
		
	}

}
