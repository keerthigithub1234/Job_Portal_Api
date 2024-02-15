package com.example.jobportalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportalapi.entity.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

}
