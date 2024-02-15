package com.example.jobportalapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportalapi.enums.BuisnessTypesEnum;
import com.example.jobportalapi.requestdto.CompanyRequestDto;
import com.example.jobportalapi.responsedto.CompanyResponseDto;
import com.example.jobportalapi.usuage.ResponseStructure;

import jakarta.validation.Valid;

public interface CompanyService {

	ResponseEntity<ResponseStructure<String>> insertCompany(@Valid CompanyRequestDto compReq, BuisnessTypesEnum buss,
			int userId);

	ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompById(int compId);

	ResponseEntity<ResponseStructure<String>> updateCompany(CompanyRequestDto compReq, int compId);

	ResponseEntity<ResponseStructure<String>> deleteCompById(int compId);





}
