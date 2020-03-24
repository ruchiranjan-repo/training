package com.demoairline.flightmonitoring.entity;
/**
 * Entity class for Airport.
 * 
 * @author Ruchi
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Airport")
public class Airport implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long airportId;
	
	@Column(name="airport_name")
	@NotNull
	private String airportName;
	
	@Column(name="airport_code")
	@NotNull
	private String airportCode;
	
	@Column(name="location")
	@NotNull
	private String location;
	
	@Column(name="no_of_runways")
	@NotNull
	private Integer noOfRunways;
	
	@Column(name="airport_type")
	@NotNull
	private String airportType;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="airportId")
	List<Runway> runways= new ArrayList<Runway>();

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getNoOfRunways() {
		return noOfRunways;
	}

	public void setNoOfRunways(Integer noOfRunways) {
		this.noOfRunways = noOfRunways;
	}

	public String getAirportType() {
		return airportType;
	}

	public void setAirportType(String airportType) {
		this.airportType = airportType;
	}

	public List<Runway> getRunways() {
		return runways;
	}

	public void setRunways(List<Runway> runways) {
		this.runways = runways;
	}

	
	
	

}
