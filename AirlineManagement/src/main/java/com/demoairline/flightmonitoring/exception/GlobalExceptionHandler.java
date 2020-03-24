package com.demoairline.flightmonitoring.exception;
/**
 * Global exception handler for airline management system.
 * 
 * @author Ruchi
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demoairline.flightmonitoring.constants.Constant;
import com.demoairline.flightmonitoring.dto.ExceptionMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler {	
	
	@ExceptionHandler(FlightsNotFoundException.class)
	public ResponseEntity<Object> flightsNotFoundException(FlightsNotFoundException flightsNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(flightsNotFoundException.getMessage(),
				flightsNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AirLineNotFoundException.class)
	public ResponseEntity<Object> airLineNotFoundException(AirLineNotFoundException airLineNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(airLineNotFoundException.getMessage(),
				airLineNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AirLinesNotFoundException.class)
	public ResponseEntity<Object> airLinesNotFoundException(AirLinesNotFoundException airLinesNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(airLinesNotFoundException.getMessage(),
				airLinesNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> airportNotFoundExceptionHandler(
			AirportNotFoundException airportNotFoundException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(airportNotFoundException.getMessage(), airportNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> flightNotFoundExceptionHandler(
			FlightNotFoundException flightNotFoundException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(flightNotFoundException.getMessage(), flightNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FlightScheduleNotFoundException.class)
	public ResponseEntity<Object> flightScheduleNotFoundExceptionHandler(
			FlightScheduleNotFoundException flightScheduleNotFoundException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(flightScheduleNotFoundException.getMessage(), flightScheduleNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRunwayAvailableException.class)
	public ResponseEntity<Object> noRunwayAvailableExceptionHandler(
			NoRunwayAvailableException noRunwayAvailableException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(noRunwayAvailableException.getMessage(), noRunwayAvailableException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AirportRunwayNotFoundException.class)
	public ResponseEntity<Object> airportRunwayNotFoundExceptionHandler(
			AirportRunwayNotFoundException airportRunwayNotFoundException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(airportRunwayNotFoundException.getMessage(), airportRunwayNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(FlightScheduleDateTimeException.class)
	public ResponseEntity<Object> flightScheduleDateTimeExceptionnHandler(
			FlightScheduleDateTimeException flightScheduleDateTimeException) {
		
		ExceptionMessageDTO exceptionMessageDTO= new ExceptionMessageDTO(flightScheduleDateTimeException.getMessage(), flightScheduleDateTimeException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FlightScheduleAlreadyDeletedException.class)
	ResponseEntity<ExceptionMessageDTO>flightScheduleAlreadyDeleted(FlightScheduleAlreadyDeletedException ex)
	{
		ExceptionMessageDTO exceptionDto=new ExceptionMessageDTO();
		exceptionDto.setMessage(ex.getMessage());
		exceptionDto.setErrorCode(Constant.FlightScheduleDeletedCode);
		return new ResponseEntity<>(exceptionDto,HttpStatus.OK);
	}
	
	
}
