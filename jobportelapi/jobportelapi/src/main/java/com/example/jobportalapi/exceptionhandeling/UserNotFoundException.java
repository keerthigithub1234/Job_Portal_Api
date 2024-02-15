package com.example.jobportalapi.exceptionhandeling;

public class UserNotFoundException extends RuntimeException {
	
	private String message;


	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	

}
