package com.demoairline.flightmonitoring.exception;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for airline management system.
 * 
 * @author Ruchi,Guruchandru,Yaseen
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demoairline.flightmonitoring.constants.Constant;
import com.demoairline.flightmonitoring.dto.ErrorResponse;
import com.demoairline.flightmonitoring.dto.ExceptionMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentNotValidException) {

			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;

			List<String> errorList = exception.getBindingResult()

					.getFieldErrors()

					.stream()

					.map(fieldError -> fieldError.getDefaultMessage())

					.collect(Collectors.toList());

			ErrorResponse errorDetails = new ErrorResponse("this is a message from handler", errorList, 856);

			return super.handleExceptionInternal(ex, errorDetails, headers, status, request);

		}
		return super.handleExceptionInternal(ex, body, headers, status, request);

	}

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
	public ResponseEntity<Object> airportNotFoundExceptionHandler(AirportNotFoundException airportNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(airportNotFoundException.getMessage(),
				airportNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> flightNotFoundExceptionHandler(FlightNotFoundException flightNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(flightNotFoundException.getMessage(),
				flightNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightScheduleNotFoundException.class)
	public ResponseEntity<Object> flightScheduleNotFoundExceptionHandler(
			FlightScheduleNotFoundException flightScheduleNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(flightScheduleNotFoundException.getMessage(),
				flightScheduleNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRunwayAvailableException.class)
	public ResponseEntity<Object> noRunwayAvailableExceptionHandler(
			NoRunwayAvailableException noRunwayAvailableException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(noRunwayAvailableException.getMessage(),
				noRunwayAvailableException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AirportRunwayNotFoundException.class)
	public ResponseEntity<Object> airportRunwayNotFoundExceptionHandler(
			AirportRunwayNotFoundException airportRunwayNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(airportRunwayNotFoundException.getMessage(),
				airportRunwayNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightScheduleDateTimeException.class)
	public ResponseEntity<Object> flightScheduleDateTimeExceptionnHandler(
			FlightScheduleDateTimeException flightScheduleDateTimeException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(flightScheduleDateTimeException.getMessage(),
				flightScheduleDateTimeException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightScheduleAlreadyDeletedException.class)
	ResponseEntity<ExceptionMessageDTO> flightScheduleAlreadyDeleted(FlightScheduleAlreadyDeletedException ex) {
		ExceptionMessageDTO exceptionDto = new ExceptionMessageDTO();
		exceptionDto.setMessage(ex.getMessage());
		exceptionDto.setErrorCode(Constant.flightScheduleDeletedCode);
		return new ResponseEntity<>(exceptionDto, HttpStatus.OK);
	}

}
