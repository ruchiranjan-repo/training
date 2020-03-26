package com.demoairline.flightmonitoring.entity;
/**
 * Entity class for Airline.
 * 
 * @author Ruchi
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="Airline")
public class Airline  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long airlineId;
	
	@Column(name="AirlineName")
	@NotNull
	private String airlineName;
	
	@Column(name="RegistrationNumber")
	@NotNull
	private String registrationNumber;
	
	@Column(name="airlineStatus")
	@NotNull	
	private String airlineStatus;

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getAirlineStatus() {
		return airlineStatus;
	}

	public void setAirlineStatus(String airlineStatus) {
		this.airlineStatus = airlineStatus;
	}
	
	

	

}
