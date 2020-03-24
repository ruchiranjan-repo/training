package com.demoairline.flightmonitoring.entity;
/**
 * Entity class for FlightSchedule.
 * 
 * @author Ruchi
 */
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FlightSchedule")
public class FlightSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scheduleId;

	@ManyToOne(cascade=CascadeType.ALL)
	Flight flight;

	@ManyToOne(cascade=CascadeType.ALL)
	Airport airport;

	@Column(name = "scheduled_date_time")
	@NotNull
	private LocalDateTime scheduledDateTime;

	@Column(name = "schedule_type")
	@NotNull
	private String scheduleType;

	@Column(name = "runway_id")
	@NotNull
	private Long runwayID;
	
	@Column(name = "flight_code")
	@NotNull
	private String flightCode;
	
	@Column(name = "scheduled_status")
	@NotNull
	private String scheduleStatus;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
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

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
	
	

}
