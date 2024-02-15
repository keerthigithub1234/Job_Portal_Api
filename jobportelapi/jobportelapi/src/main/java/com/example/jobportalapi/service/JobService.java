package com.example.jobportalapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportalapi.requestdto.JobRequestDto;
import com.example.jobportalapi.responsedto.JobResponseDto;
import com.example.jobportalapi.usuage.ResponseStructure;

public interface JobService {

	ResponseEntity<ResponseStructure<String>> insertJOb(JobRequestDto jobReq, int compId);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJOb(String designation);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJObBySkill(String skill);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJObLocation(String loc);

	ResponseEntity<ResponseStructure<String>> updateJobById(JobRequestDto jobReq, int jobId);

	ResponseEntity<ResponseStructure<String>> deleteJOb(int jobId);


	
}
