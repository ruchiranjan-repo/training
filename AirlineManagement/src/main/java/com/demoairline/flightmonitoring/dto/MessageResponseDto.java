package com.demoairline.flightmonitoring.dto;
/**
 * Response DTO 
 * @author Ruchi
 *
 */
public class MessageResponseDto {
	
	private String message;
	
	private Long statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
