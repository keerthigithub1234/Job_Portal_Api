package com.example.jobportalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportalapi.entity.JobApplication;

@Repository
public interface JobApplicationRepository  extends JpaRepository<JobApplication, Integer>{

}
