package com.example.jobportalapi.exceptionhandeling;

public class SkillNotFoundException extends RuntimeException{
	
	String message;

	public SkillNotFoundException(String message) {
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
