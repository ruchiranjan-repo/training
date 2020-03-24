package com.demoairline.flightmonitoring.dto;

import java.time.LocalDateTime;

public class ScheduleResponseDto {

	private Long scheduleId;

	private LocalDateTime scheduledDateTime;

	private String scheduleType;

	private Long runwayID;

	private String scheduleStatus;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
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

	public Long getRunwayID() {
		return runwayID;
	}

	public void setRunwayID(Long runwayID) {
		this.runwayID = runwayID;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

}
