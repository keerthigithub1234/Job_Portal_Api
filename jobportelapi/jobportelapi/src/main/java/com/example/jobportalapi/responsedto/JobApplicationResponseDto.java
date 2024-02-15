package com.example.jobportalapi.responsedto;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.jobportalapi.enums.ApplicationStatus;

@Component
public class JobApplicationResponseDto {
	
	private int applicationId;
	private LocalDate appliedDate;
	private ApplicationStatus status;
	private float skillMatching;
	private boolean locationMatching;
	private HashMap<String, String> options;
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public float getSkillMatching() {
		return skillMatching;
	}
	public void setSkillMatching(float skillMatching) {
		this.skillMatching = skillMatching;
	}
	public boolean isLocationMatching() {
		return locationMatching;
	}
	public void setLocationMatching(boolean locationMatching) {
		this.locationMatching = locationMatching;
	}
	public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}
	
	
	
	

}
