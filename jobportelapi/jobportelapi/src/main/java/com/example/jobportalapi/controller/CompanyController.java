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

import com.example.jobportalapi.enums.BuisnessTypesEnum;
import com.example.jobportalapi.exceptionhandeling.CompanyNotFoundException;
import com.example.jobportalapi.exceptionhandeling.UserNotFoundException;
import com.example.jobportalapi.requestdto.CompanyRequestDto;
import com.example.jobportalapi.responsedto.CompanyResponseDto;
import com.example.jobportalapi.service.CompanyService;
import com.example.jobportalapi.usuage.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@PostMapping("/users/{userId}/BusinessType/{buss}/companies")  
	public ResponseEntity<ResponseStructure<String>> inserCompany(@RequestBody @Valid CompanyRequestDto compReq,@PathVariable int userId,
			@PathVariable BuisnessTypesEnum buss) throws IllegalAccessException, UserNotFoundException
	{

		return companyService.insertCompany(compReq,buss,userId);

	}
	
	@GetMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompById(@PathVariable int  compId) throws CompanyNotFoundException
	{

		return companyService.findCompById(compId);

	}
	
	@PutMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<String>> updateUser(@RequestBody CompanyRequestDto compReq,@PathVariable int compId) throws CompanyNotFoundException 
	{

		return companyService.updateCompany(compReq,compId);

	}	
	

	@DeleteMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<String>> deleteCompById(@PathVariable int  compId) throws CompanyNotFoundException
	{

		return companyService.deleteCompById(compId);

	}

	

	
}

