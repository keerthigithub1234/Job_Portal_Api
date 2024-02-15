package com.example.jobportalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportalapi.entity.WorkExperience;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {

}
