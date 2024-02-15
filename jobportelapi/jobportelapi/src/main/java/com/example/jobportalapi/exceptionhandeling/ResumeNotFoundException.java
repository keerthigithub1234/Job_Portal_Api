package com.example.jobportalapi.exceptionhandeling;

public class ResumeNotFoundException extends RuntimeException {
	
	String message;

	public ResumeNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
