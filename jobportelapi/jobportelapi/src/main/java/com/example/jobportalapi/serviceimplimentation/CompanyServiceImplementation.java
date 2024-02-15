package com.example.jobportalapi.serviceimplimentation;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import com.example.jobportalapi.entity.Company;
import com.example.jobportalapi.entity.User;
import com.example.jobportalapi.enums.BuisnessTypesEnum;
import com.example.jobportalapi.enums.UserRole;
import com.example.jobportalapi.exceptionhandeling.CompanyNotFoundException;
import com.example.jobportalapi.exceptionhandeling.IllegalAccessException;
import com.example.jobportalapi.exceptionhandeling.UserNotFoundException;
import com.example.jobportalapi.repository.CompanyRepository;
import com.example.jobportalapi.repository.UserRepository;
import com.example.jobportalapi.requestdto.CompanyRequestDto;
import com.example.jobportalapi.responsedto.CompanyResponseDto;
import com.example.jobportalapi.service.CompanyService;
import com.example.jobportalapi.usuage.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class CompanyServiceImplementation implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userrepository;

	private Company convertToCompany(CompanyRequestDto compRq, Company comp) {

		comp.setCompanyName(compRq.getCompanyName());
		comp.setContactEmail(compRq.getContactEmail());
		comp.setContactPhno(compRq.getContactPhno());
		comp.setDescription(compRq.getDescription());
		comp.setFoundedDate(compRq.getFoundedDate());
		comp.setWebsite(compRq.getWebsite());

		return comp;
	}

	private CompanyResponseDto convertToCompResponse(Company comp) {
		CompanyResponseDto respDto = new CompanyResponseDto();
		respDto.setBusinessType(comp.getBusinessType());
		respDto.setCompanyId(comp.getCompanyId());
		respDto.setCompanyName(comp.getCompanyName());
		respDto.setContactEmail(comp.getContactEmail());
		respDto.setContactPhno(comp.getDescription());
		respDto.setDescription(comp.getDescription());
		respDto.setFoundedDate(comp.getFoundedDate());
		respDto.setWebsite(comp.getWebsite());
		return respDto;

	}

	@Override
	public ResponseEntity<ResponseStructure<String>> insertCompany(@Valid CompanyRequestDto compReq,
			BuisnessTypesEnum buss, int userId) {

		Optional<User> optuser = userrepository.findById(userId);

		if(optuser.isPresent())
		{
			User user = optuser.get();
			if(user.getUserRole()==UserRole.EMPLOYER)
			{
				Company newcompany = convertToCompany(compReq, new Company());
				newcompany.setBusinessType(buss);
				newcompany.setUserMap(user);

				companyRepository.save(newcompany);
				
				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setData("data saved successfully");
				rs.setMessage("new company added successfully");
				rs.setStatusCode(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CREATED);
			}
			else
			{
				throw new IllegalAccessException("user is not authorised to do this operation");
			}
		}
		else
			throw new UserNotFoundException("User Not Found");

	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompById(int compId) {
		
		Optional<Company> optuser = companyRepository.findById(compId);
		if(optuser.isPresent())
		{
			Company company = optuser.get();
			
			CompanyResponseDto dto = convertToCompResponse(company);
			HashMap<String, String> hm  = new HashMap<>();
			hm.put("Founder", "/users/"+company.getUserMap().getUserId());
			
			
			ResponseStructure<CompanyResponseDto> rs = new ResponseStructure<CompanyResponseDto>();
			rs.setData(dto);
			rs.setMessage("company data found sucessfully");
			rs.setStatusCode(HttpStatus.FOUND.value());
			
			
			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(rs,HttpStatus.FOUND);
			
			
		}
		else
			throw new CompanyNotFoundException("Company with given Id is not present");	
		
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> updateCompany(CompanyRequestDto compReq, int compId) {
		
		Optional<Company> optuser = companyRepository.findById(compId);
		if(optuser.isPresent())
		{
			Company company = convertToCompany(compReq, optuser.get());
			
			companyRepository.save(company);
			
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setData("company added successfully");
			rs.setMessage("company data  updated successfully");
			rs.setStatusCode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ACCEPTED);
		}
		else throw new CompanyNotFoundException("company not found with given Id");
		
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteCompById(int compId) {
		
		Optional<Company> optuser = companyRepository.findById(compId);
		if(optuser.isPresent())
		{
			Company company = optuser.get();
			companyRepository.delete(company);
			
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setData("company data deleted ");
			rs.setMessage("company data found successfully");
			rs.setStatusCode(HttpStatus.FOUND.value());
			
			return  new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.FOUND);
		}
		else
			throw new CompanyNotFoundException("company with id mentioned is not present");
		
		
		
		
		
	}



}
