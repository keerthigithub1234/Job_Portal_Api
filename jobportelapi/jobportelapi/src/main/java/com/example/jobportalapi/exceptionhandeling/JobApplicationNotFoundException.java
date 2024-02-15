package com.example.jobportalapi.exceptionhandeling;

public class JobApplicationNotFoundException  extends RuntimeException{
	
	String message;

	public JobApplicationNotFoundException(String message) {
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
