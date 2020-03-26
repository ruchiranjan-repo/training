package com.demoairline.flightmonitoring.entity;

/**
 * Entity class for Runway.
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
@Table(name = "runway")
public class Runway implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long runwayID;

	@Column(name = "runwa_number")
	@NotNull
	private String runwayNumber;

	public Long getRunwayID() {
		return runwayID;
	}

	public void setRunwayID(Long runwayID) {
		this.runwayID = runwayID;
	}

	public String getRunwayNumber() {
		return runwayNumber;
	}

	public void setRunwayNumber(String runwayNumber) {
		this.runwayNumber = runwayNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
