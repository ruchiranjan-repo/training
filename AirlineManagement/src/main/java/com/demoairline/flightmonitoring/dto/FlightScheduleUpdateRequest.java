package com.demoairline.flightmonitoring.dto;

import java.time.LocalDateTime;
/**
 * Request class for updating flight schedule.
 * 
 * @author Ruchi
 */
public class FlightScheduleUpdateRequest {
private Long scheduleId;
	
	private LocalDateTime scheduledDateTime;
	
	private Long runwayId;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getRunwayId() {
		return runwayId;
	}

	public void setRunwayId(Long runwayId) {
		this.runwayId = runwayId;
	}

	
	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}


}
