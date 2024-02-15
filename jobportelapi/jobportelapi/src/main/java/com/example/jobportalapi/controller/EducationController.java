package com.example.jobportalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.usuage.ResponseStructure;
import com.example.jobportalapi.enums.EducationType;
import com.example.jobportalapi.exceptionhandeling.EducationNotFoundException;
import com.example.jobportalapi.exceptionhandeling.ResumeNotFoundException;
import com.example.jobportalapi.requestdto.EducationRequestDto;
import com.example.jobportalapi.responsedto.EducationResponseDto;
import com.example.jobportalapi.service.EducationService;

import jakarta.validation.Valid;

@RestController
public class EducationController {
	
	@Autowired
	EducationService educationService;



	@PostMapping("/resumes/{resumeId}/educationTypes/{eduType}/educations")  
	public ResponseEntity<ResponseStructure<String>> inserEducation(@RequestBody @Valid EducationRequestDto eduReq,@PathVariable int resumeId ,@PathVariable EducationType eduType
			) throws ResumeNotFoundException, IllegalAccessException 
	{

		return educationService.insertEducation(eduReq,resumeId,eduType);

	}

	@PutMapping("/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<String>> updateEducation(@RequestBody @Valid EducationRequestDto eduReq,@PathVariable int eduId 
			) throws EducationNotFoundException  
	{

		return educationService.updateEducation(eduReq,eduId);

	}

	@GetMapping("/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<EducationResponseDto>> findEducationById(@PathVariable int eduId 
			) throws EducationNotFoundException  
	{

		return educationService.findEducationById(eduId);
	}		

	@GetMapping("/resumes/{resumeId}/educations")  
	public ResponseEntity<ResponseStructure<List<EducationResponseDto>>> findEducationByResumeId(@PathVariable int resumeId 
			) throws EducationNotFoundException, ResumeNotFoundException   
	{

		return educationService.findEducationByResumeId(resumeId);
	}

	@DeleteMapping("/resumes/{resumeId}/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<String>> deleteEducationByResumeId(@PathVariable int resumeId, 
			@PathVariable int eduId		) throws EducationNotFoundException, ResumeNotFoundException   
	{

		return educationService.deleteEducationByResumeId(resumeId,eduId);
	}

}
