package com.example.jobportalapi.exceptionhandeling;

public class WorkExperienceNotFoundExeption  extends RuntimeException{
	
	String message;

	public WorkExperienceNotFoundExeption(String message) {
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
