package com.ecommercedemo.ecommerce.dto;

/**
 * To send error message with status code
 * @author YaseenShaik
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionMessageDTO {
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("statusCode")
	private Integer statusCode;

	@JsonProperty("errorList")
	private List<String> errorList;

	public ExceptionMessageDTO(String errorMessage, Integer statusCode) {
		super();
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	public ExceptionMessageDTO(String errorMessage, List<String> errorList, Integer statusCode) {
		this.errorMessage = errorMessage;
		this.errorList = errorList;
		this.statusCode = statusCode;
	}

	@JsonProperty("errorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	@JsonProperty("errorMessage")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JsonProperty("statusCode")
	public Integer getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
}
