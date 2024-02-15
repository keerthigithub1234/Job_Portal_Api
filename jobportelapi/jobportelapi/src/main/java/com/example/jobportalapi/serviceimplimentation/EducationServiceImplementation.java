package com.example.jobportalapi.serviceimplimentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.imdb.usuage.ResponseStructure;
import com.example.jobportalapi.entity.Education;
import com.example.jobportalapi.entity.Resume;
import com.example.jobportalapi.enums.EducationType;
import com.example.jobportalapi.exceptionhandeling.EducationNotFoundException;
import com.example.jobportalapi.exceptionhandeling.ResumeNotFoundException;
import com.example.jobportalapi.repository.EducationRepository;
import com.example.jobportalapi.repository.ResumeRepository;
import com.example.jobportalapi.requestdto.EducationRequestDto;
import com.example.jobportalapi.responsedto.EducationResponseDto;
import com.example.jobportalapi.service.EducationService;

import jakarta.validation.Valid;

@Service
public class EducationServiceImplementation implements EducationService {

	@Autowired
	private EducationRepository eduRepo;
	@Autowired
	private ResumeRepository resumRepo;

	private Education convertToEducation(EducationRequestDto eduReq ,Education edu)
	{

		edu.setEnddate(eduReq.getEnddate());
		edu.setGradStatus(eduReq.getGradStatus());
		edu.setInsituteName(eduReq.getInsituteName());
		edu.setLocation(eduReq.getLocation());
		edu.setPercentageOrCGPA(eduReq.getPercentageOrCGPA());
		edu.setStartDate(eduReq.getStartDate());
		edu.setStream(eduReq.getStream());
		edu.setDegreeType(eduReq.getDegreeType());
		return edu;
	}

	private EducationResponseDto convertToEducResponse (Education edu)
	{
		EducationResponseDto dto = new EducationResponseDto();
		dto.setDegreeType(edu.getDegreeType());
		dto.setEduId(edu.getEduId());
		dto.setEduType(edu.getEduType());
		dto.setEnddate(edu.getEnddate());
		dto.setGradStatus(edu.getGradStatus());
		dto.setInsituteName(edu.getInsituteName());
		dto.setLocation(edu.getLocation());
		dto.setPercentageOrCGPA(edu.getPercentageOrCGPA());
		dto.setStartDate(edu.getStartDate());
		dto.setStream(edu.getStream());


		return dto;
	}
	
	public ResponseEntity<ResponseStructure<String>> insertEducation(@Valid EducationRequestDto eduReq,
			int resumeId, EducationType eduType) throws ResumeNotFoundException, IllegalAccessException {

		Optional<Resume> optResume = resumRepo.findById(resumeId);
		if(optResume.isPresent()) {
			Resume resume = optResume.get();

			Education education = convertToEducation(eduReq, new Education());

			//education.setAssociatedResume(resume);

			List<Education> eduList = resume.getEduList();

			if(!eduList.isEmpty()) {

				for(Education ed: eduList) {


					if( eduType==ed.getEduType()&&ed.getEduType()==EducationType.SSLC) {
						throw new IllegalAccessException("SSLC education cannot be added more than one times");

					}
					else if (eduType== ed.getEduType()&&ed.getEduType()==EducationType.PUC)
					{
						throw new IllegalAccessException(" PUC education cannot be added more than one times");

					}
				}

			}

			education.setEduType(eduType);
			if(eduType==EducationType.SSLC) { education.setDegreeType(null); education.setStream(null);}

			eduRepo.save(education);


			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" education data saved  successfully");  
			respStruc.setData(" EDUCATION ADDED  SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		}

		else throw new ResumeNotFoundException("Resume with given Id not present");




	}



	public ResponseEntity<ResponseStructure<String>> updateEducation(@Valid EducationRequestDto eduReq, int eduId) throws EducationNotFoundException {

		Optional<Education> optEdu = eduRepo.findById(eduId);


		if(optEdu.isPresent()) {

			Education eduExisting = optEdu.get();

			Education eduUpdate = convertToEducation(eduReq, eduExisting);


			eduRepo.save(eduUpdate);


			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
			respStruc.setMessage(" education data updated  successfully");
			respStruc.setData(" EDUCATION UPDATED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		}

		else throw new EducationNotFoundException(" Education with given Id not present");


	}




	public ResponseEntity<ResponseStructure<EducationResponseDto>> findEducationById(int eduId) throws EducationNotFoundException {
		Optional<Education> optEdu = eduRepo.findById(eduId);


		if(optEdu.isPresent()) {

			Education education = optEdu.get();
			EducationResponseDto dto = convertToEducResponse(education);
			HashMap<String,String> hashMap = new HashMap<>();

			hashMap.put("Applicant","/resumes/"+education.getAssociatedResume().getResumeId());
			dto.setOptions(hashMap);

			ResponseStructure<EducationResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.FOUND.value());

			respStruc.setMessage(" education data fetched  successfully");
			respStruc.setData(dto);

			return new ResponseEntity<ResponseStructure<EducationResponseDto>>(respStruc, HttpStatus.FOUND);
		}

		else throw new EducationNotFoundException(" Education with given Id not present");

	}







	public ResponseEntity<ResponseStructure<List<EducationResponseDto>>> findEducationByResumeId(int resumeId) throws EducationNotFoundException, ResumeNotFoundException {

		Optional<Resume> optResume = resumRepo.findById(resumeId);

		if(optResume.isPresent()) {

			Resume resume = optResume.get();

			List<Education> eduList = resume.getEduList();

			if(!eduList.isEmpty()) {
				List<EducationResponseDto> resList = new ArrayList<>();
				for(Education edu : eduList) {


					EducationResponseDto dto = convertToEducResponse(edu);
					HashMap<String,String> hashMap = new HashMap<>();

					hashMap.put("Applicant","/resumes/"+resumeId);
					dto.setOptions(hashMap);

					resList.add(dto);

				}
				ResponseStructure<List<EducationResponseDto>>respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.FOUND.value());
				respStruc.setMessage(" education data fetched  successfully");
				respStruc.setData(resList);

				return new ResponseEntity<ResponseStructure<List<EducationResponseDto>>>(respStruc, HttpStatus.FOUND);

			}
			else throw new EducationNotFoundException("education for this applicant not present");
		}
		else throw new ResumeNotFoundException(" REsume with given Id not present");
	}

	public ResponseEntity<ResponseStructure<String>> deleteEducationByResumeId(int resumeId,
			int eduId) throws ResumeNotFoundException, EducationNotFoundException {
		Optional<Resume> optResume = resumRepo.findById(resumeId);

		if(optResume.isPresent()) {

			Resume resume = optResume.get();

			List<Education> eduList = resume.getEduList();

			if(!eduList.isEmpty()) {

				boolean flag=false;
				for(Education edu : eduList) {

					if(edu.getEduId()==eduId) {

						eduList.remove(edu);
						resume.setEduList(eduList); 
						eduRepo.deleteById(eduId); flag=true; 


						break;}

				}

				if(flag==false) throw new EducationNotFoundException("education with given Id not present");


				ResponseStructure<String>respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.FOUND.value());
				respStruc.setMessage(" education data deleted  successfully");
				respStruc.setData("EDUCATION DATA DELETED AND RESUME UPDATED SUCCESSFULY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.FOUND);


			}
			else throw new EducationNotFoundException("education for this applicant not present");
		}

		else throw new ResumeNotFoundException(" REsume with given Id not present");

	}

	
	
	

}
