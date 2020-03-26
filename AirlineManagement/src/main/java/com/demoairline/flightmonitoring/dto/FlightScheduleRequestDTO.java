package com.demoairline.flightmonitoring.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Request claas for adding flight schedule.
 * 
 * @author Ruchi
 */
public class FlightScheduleRequestDTO {

	@NotNull(message = "flight id required")
	private Long flightId;
	@NotNull(message = "airoplane id required")
	private Long airportId;
	private LocalDateTime scheduledDateTime;
	private String scheduleType;

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	

	

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

}
