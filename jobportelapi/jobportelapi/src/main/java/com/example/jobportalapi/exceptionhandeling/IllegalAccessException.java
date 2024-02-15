package com.example.jobportalapi.exceptionhandeling;

public class IllegalAccessException extends RuntimeException {
	
	String message;

	public IllegalAccessException(String message) {
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
