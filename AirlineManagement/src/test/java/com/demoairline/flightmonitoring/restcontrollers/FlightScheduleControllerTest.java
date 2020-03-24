package  com.demoairline.flightmonitoring.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demoairline.flightmonitoring.dto.CancelScheduleDto;
import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;
import com.demoairline.flightmonitoring.dto.ScheduleResponseDto;
import com.demoairline.flightmonitoring.entity.FlightSchedule;
import com.demoairline.flightmonitoring.services.FlightScheduleService;


@RunWith(MockitoJUnitRunner.class)
public class FlightScheduleControllerTest {
	@Mock
	private FlightScheduleService flightScheduleService;
	@InjectMocks
	private FlightScheduleRestController flightScheduleController;


	
	@Test
	public void tesCancelSchedule()  {
		FlightSchedule schedule = new FlightSchedule();
		schedule.setScheduleId(1000L);
		schedule.setRunwayID(1234L);
		schedule.setScheduleStatus("available");
		schedule.setScheduleType("Arrival");
		CancelScheduleDto cancelScheduleDto = new CancelScheduleDto();
		cancelScheduleDto.setMeassge("cancel sucessfully");
		cancelScheduleDto.setStatusCode(499L);
		Mockito.when(flightScheduleService.cancelScheduleByScheduleId(1000L)).thenReturn(cancelScheduleDto);
		ResponseEntity<CancelScheduleDto> response = flightScheduleController.cancelSchedule(1000L);
		assertEquals(cancelScheduleDto.getMeassge(), response.getBody().getMeassge());

	}


	@Test
	public void testFlightScheduleByFlightCode(){
		FlightSchedule flightSchedule = new FlightSchedule();
		flightSchedule.setFlightCode("fhf");
		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
		scheduleResponseDto.setScheduleId(1000L);
		scheduleResponseDto.setScheduleType("commercial");
		scheduleResponseDto.setRunwayID(1L);
		scheduleResponseDto.setScheduleStatus("available");
		List<ScheduleResponseDto> Listschedule = new ArrayList<ScheduleResponseDto>();
		Listschedule.add(scheduleResponseDto);
		Mockito.when(flightScheduleService.getFlightScheduleByFlightCode("fhf")).thenReturn(Listschedule);
		ResponseEntity<List<ScheduleResponseDto>>response=flightScheduleController.getScheduleByFlightCode("fhf");
		assertEquals(Listschedule.size(), response.getBody().size());
	}
	@Test
	public void testAddFlightSchedule() {
		FlightScheduleRequestDTO flightScheduleRequestDTO = new FlightScheduleRequestDTO();
		flightScheduleRequestDTO.setFlightId(1000L);
		flightScheduleRequestDTO.setAirportId(2000L);
		flightScheduleRequestDTO.setScheduleType("arrival");
		MessageResponseDto messageResponseDto = new MessageResponseDto();
		messageResponseDto.setMessage("add flight successfully");
		Mockito.when(flightScheduleService.addFlightSchedule(flightScheduleRequestDTO)).thenReturn(messageResponseDto);
		ResponseEntity<Object> response = flightScheduleController.addFlightSchedule(flightScheduleRequestDTO);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	public void tesUpdateFlightSchedule() {

		FlightScheduleUpdateRequest flightScheduleRequestDTO = new FlightScheduleUpdateRequest();
		flightScheduleRequestDTO.setRunwayId(1000L);
		flightScheduleRequestDTO.setScheduleId(2000L);

		MessageResponseDto messageResponseDto = new MessageResponseDto();
		messageResponseDto.setMessage("UpdatesSuccesfully flight successfully");
		Mockito.when(flightScheduleService.updateFlightSchedule(flightScheduleRequestDTO))
				.thenReturn(messageResponseDto);
		ResponseEntity<Object> response = flightScheduleController.updateFlightSchedule(flightScheduleRequestDTO);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
}
