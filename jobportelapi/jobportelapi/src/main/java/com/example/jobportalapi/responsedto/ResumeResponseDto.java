package com.example.jobportalapi.responsedto;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class ResumeResponseDto {
	
	private int resumeId;
	private String objective;
	private HashMap<String,String> options;
	
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}
	
	

}
