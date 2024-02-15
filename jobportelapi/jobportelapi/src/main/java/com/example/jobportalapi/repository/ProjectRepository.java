package com.example.jobportalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportalapi.entity.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Integer>{

}
