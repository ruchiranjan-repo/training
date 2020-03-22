package com.hcl.banking.dto;

public class ExceptionDTO {
	
	String message;
	
	Long errorCode;

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

	public ExceptionDTO(String message, Long errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	
	

}
