package com.example.jobportalapi.requestdto;

import org.springframework.stereotype.Component;

@Component
public class ResumeRequestDto {
	
	private String objective;

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	

}
