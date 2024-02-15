package com.example.jobportalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportalapi.entity.Resume;

@Repository
public interface ResumeRepository  extends JpaRepository<Resume, Integer>{

}
