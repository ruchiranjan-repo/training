package com.demoairline.flightmonitoring.dto;

/**
 * Exception message response DTO.
 * 
 * @author Ruchi
 */
public class ExceptionMessageDTO {
	
	private String message;
	private Long errorCode;
	public ExceptionMessageDTO()
	{
		
	}
	
	public ExceptionMessageDTO(String message, Long errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

}
