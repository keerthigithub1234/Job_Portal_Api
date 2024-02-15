package com.example.jobportalapi.exceptionhandeling;

public class EducationNotFoundException extends RuntimeException {
	
	String message;

	public EducationNotFoundException(String message) {
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
