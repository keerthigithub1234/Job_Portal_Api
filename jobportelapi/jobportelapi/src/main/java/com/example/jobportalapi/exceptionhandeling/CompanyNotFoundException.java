package com.example.jobportalapi.exceptionhandeling;

public class CompanyNotFoundException extends RuntimeException {
	
	String message;

	public CompanyNotFoundException(String message) {
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
