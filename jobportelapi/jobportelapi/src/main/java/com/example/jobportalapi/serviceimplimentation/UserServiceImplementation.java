package com.example.jobportalapi.serviceimplimentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportalapi.entity.User;
import com.example.jobportalapi.enums.UserRole;
import com.example.jobportalapi.exceptionhandeling.UserNotFoundException;
import com.example.jobportalapi.repository.UserRepository;
import com.example.jobportalapi.requestdto.UserRequestDto;
import com.example.jobportalapi.responsedto.UserResponseDto;
import com.example.jobportalapi.service.UserService;
import com.example.jobportalapi.usuage.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	private User convertToUser(UserRequestDto userRq, User user) {
		user.setEmail(userRq.getEmail());
		user.setPassword(userRq.getPassword());
		user.setUsername(userRq.getUsername());

		return user;
	}

	private UserResponseDto convertToUserRespnse(User user) {
		UserResponseDto userResp = new UserResponseDto();

		userResp.setEmail(user.getEmail());
		userResp.setUserId(user.getUserId());
		userResp.setUsername(user.getUsername());
		userResp.setUserrole(user.getUserRole());
		return userResp;

	}

	@Override
	public ResponseEntity<ResponseStructure<String>> insertUser(@Valid UserRequestDto userReq, UserRole role) {

		User newuser = convertToUser(userReq, new User());
		newuser.setUserRole(role);
		userRepository.save(newuser);

		ResponseStructure<String> rs =new ResponseStructure<>();
		rs.setData("user saved successfully");
		rs.setMessage(" 1 user data added successfully");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<String>>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(int userId) {
		Optional<User> optuser = userRepository.findById(userId);
		if(optuser.isPresent())
		{
			UserResponseDto user = convertToUserRespnse(optuser.get());

			ResponseStructure<UserResponseDto> rs = new ResponseStructure<>();
			rs.setData(user);
			rs.setMessage("user data found");
			rs.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<UserResponseDto>>(HttpStatus.FOUND);
		}
		else
			throw new  UserNotFoundException("user not found with id");
		
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> updateUserById(@Valid UserRequestDto userReq, int userId) {
		
		Optional<User> optuser = userRepository.findById(userId);
		if(optuser.isPresent())
		{
			User user = convertToUser(userReq, optuser.get());
			userRepository.save(user);
			
			ResponseStructure<String> rs = new ResponseStructure<String>();
			rs.setData("user data added successfully");
			rs.setMessage("user data updated successfully");
			rs.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ACCEPTED);
			
		}
		else 
			throw new UserNotFoundException("user not found");
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteUserById(int userId) {
		
		Optional<User> optuser = userRepository.findById(userId);
		if(optuser.isPresent())
		{
			userRepository.delete(optuser.get());
			
			ResponseStructure<String> rs = new ResponseStructure<String>();
			rs.setData("user data Deleted successfully");
			rs.setMessage("user data Deleted successfully");
			rs.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.FOUND);
		}
		
		else
			throw new UserNotFoundException("user with id not found");
	}






}


