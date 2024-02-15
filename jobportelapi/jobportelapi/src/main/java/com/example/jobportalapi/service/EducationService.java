package com.example.jobportalapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.imdb.usuage.ResponseStructure;
import com.example.jobportalapi.enums.EducationType;
import com.example.jobportalapi.exceptionhandeling.ResumeNotFoundException;
import com.example.jobportalapi.requestdto.EducationRequestDto;
import com.example.jobportalapi.responsedto.EducationResponseDto;

import jakarta.validation.Valid;

public interface EducationService {

	ResponseEntity<ResponseStructure<String>> insertEducation(@Valid EducationRequestDto eduReq, int resumeId,
			EducationType eduType) throws ResumeNotFoundException, IllegalAccessException;

	ResponseEntity<ResponseStructure<String>> updateEducation(@Valid EducationRequestDto eduReq, int eduId);

	ResponseEntity<ResponseStructure<EducationResponseDto>> findEducationById(int eduId);

	ResponseEntity<ResponseStructure<List<EducationResponseDto>>> findEducationByResumeId(int resumeId);

	ResponseEntity<ResponseStructure<String>> deleteEducationByResumeId(int resumeId, int eduId);



}
