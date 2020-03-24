package com.demoairline.flightmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelScheduleDto {
	@JsonProperty("meassge")
	private String meassge;
	@JsonProperty("statusCode")
	private Long statusCode;
	@JsonProperty("meassge")
	public String getMeassge() {
		return meassge;
	}
	@JsonProperty("meassge")
	public void setMeassge(String meassge) {
		this.meassge = meassge;
	}
	@JsonProperty("statusCode")
	public Long getStatusCode() {
		return statusCode;
	}
	@JsonProperty("statusCode")
	public void setStatusCode(Long cancelcode) {
		this.statusCode = cancelcode;
	}

}
