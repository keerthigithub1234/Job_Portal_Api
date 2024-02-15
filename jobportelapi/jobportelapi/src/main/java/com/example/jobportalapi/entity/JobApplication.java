package com.example.jobportalapi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.jobportalapi.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicationId;
	private LocalDate appliedDate;
	private ApplicationStatus status;
	private float skillMatching;
	private boolean locationMatching;
	
	@ManyToOne
	private User Applicant;
	
	@ManyToOne
	private Job requirement;

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

	public User getApplicant() {
		return Applicant;
	}

	public void setApplicant(User applicant) {
		Applicant = applicant;
	}

	public Job getRequirement() {
		return requirement;
	}

	public void setRequirement(Job requirement) {
		this.requirement = requirement;
	}
	
	

}
