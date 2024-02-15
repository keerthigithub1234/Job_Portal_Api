package com.example.jobportalapi.exceptionhandeling;

public class JobNotFoundException  extends RuntimeException{
	
	String message;

	public JobNotFoundException(String message) {
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
