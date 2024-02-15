package com.example.jobportalapi.service;

import org.springframework.http.ResponseEntity;

import com.example.jobportalapi.enums.UserRole;
import com.example.jobportalapi.requestdto.JobRequestDto;
import com.example.jobportalapi.requestdto.UserRequestDto;
import com.example.jobportalapi.responsedto.JobResponseDto;
import com.example.jobportalapi.responsedto.UserResponseDto;
import com.example.jobportalapi.usuage.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService   {

	ResponseEntity<ResponseStructure<String>> insertUser(@Valid UserRequestDto userReq, UserRole role);

	ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(int userId);

	ResponseEntity<ResponseStructure<String>> updateUserById(@Valid UserRequestDto userReq, int userId);

	ResponseEntity<ResponseStructure<String>> deleteUserById(int userId);


	

	

	

	
	
	

}
