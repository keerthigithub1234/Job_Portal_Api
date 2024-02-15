package com.example.jobportalapi.exceptionhandeling;

public class SocialProfileNotFoundException extends RuntimeException {

	String message;

	public SocialProfileNotFoundException(String message) {
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
