package com.resume.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.model.CertificationsDetail;
import com.resume.model.EducationDetails;
import com.resume.model.PersonalDetails;
import com.resume.model.ProfessionalDetails;
import com.resume.model.ProjectsDetails;
import com.resume.repository.CertificationsDetailRepo;
import com.resume.repository.EducationDetailsRepo;
import com.resume.repository.PersonalDetailsRepo;
import com.resume.repository.ProfessionalDetailsRepo;
import com.resume.repository.ProjectsDetailRepo;

@Service
public class AllDetailsService {
	
	@Autowired
	PersonalDetailsRepo personalDetailsRepo;
	
	@Autowired
	CertificationsDetailRepo certificationsDetailRepo;
	
	@Autowired
	ProfessionalDetailsRepo professionalDetailsRepo;
	
	@Autowired
	EducationDetailsRepo educationDetailsRepo;
	
	@Autowired
	ProjectsDetailRepo projectsDetailRepo;
	
	/**
	 * 
	 * @param userDetails
	 * @return, an object of PersonalDetails which will not have password field
	 */
	public PersonalDetails getUserDetail(PersonalDetails userDetails) {
		PersonalDetails userDetails1 = new PersonalDetails(userDetails.getUserId(), userDetails.getFirstName(),
				userDetails.getLastName(), userDetails.getDob(), userDetails.getContactNumber(), userDetails.getEmailId(),
				userDetails.getAddress(), userDetails.getLinkedInId());
		
		return userDetails1;
	}
	
	/**
	 * 
	 * @param userId
	 * @return, PersonalDetails of a user
	 */
	public PersonalDetails getUserById(int userId) {
		
		Optional<PersonalDetails> userDetails = personalDetailsRepo.findById(userId);
		
		return this.getUserDetail(userDetails.get());
	}
	
	/**
	 * 
	 * @param userId, used to get the user by userId
	 * @return, all certification details of a user
	 */
	public List<CertificationsDetail> getCertificationsDetailById(int userId){
		
		List<CertificationsDetail> allCertificationDetails = certificationsDetailRepo.findCertificationDetailsById(userId);

		
		return allCertificationDetails;
	}
	
	/**
	 * 
	 * @param userId, used to get the user by userId
	 * @return, all Professional details of a user
	 */
	public List<ProfessionalDetails> getProfessionalDetailsById(int userId){
		
		List<ProfessionalDetails> allProfessionalDetails = professionalDetailsRepo.findProfessionalDetailsById(userId);
		
		return allProfessionalDetails;
	}
	
	/**
	 * 
	 * @param userId, used to get the user by userId
	 * @return, all Education details of a user
	 */
	public List<EducationDetails> getEducationDetailsById(int userId){
		
		List<EducationDetails> allEducationDetails = educationDetailsRepo.findEducationDetailsById(userId);
		
		return allEducationDetails;
	}
	
	/**
	 * 
	 * @param userId, used to get the user by userId
	 * @return, all Projects details of a user
	 */
	public List<ProjectsDetails> getProjectDetailsById(int userId){
		
		List<ProjectsDetails> allProjectDetails = projectsDetailRepo.findProjectsDetailsById(userId);
		
		return allProjectDetails;
	}
	
	/**
	 * 
	 * @param userId
	 * @return Array Of PersonalDetails, ProjectsDetails, CertificationDetails, ProfessionalDetails &
	 *  EducationDetails to show user all data.
	 */
	public JSONArray getAllDetails(int userId) {
		
		PersonalDetails personalDetail = this.getUserById(userId);
		List<CertificationsDetail> allCertificationDetails = this.getCertificationsDetailById(userId);
		List<ProfessionalDetails> allProfessionalDetails = this.getProfessionalDetailsById(userId);
		List<EducationDetails> allEducationDetails = this.getEducationDetailsById(userId);
		List<ProjectsDetails> allProjectDetails = this.getProjectDetailsById(userId);
		
		JSONArray detailsArray = new JSONArray();
		JSONObject detailsObj = new JSONObject();
		detailsObj.put("PersonalDetails", personalDetail);
		detailsObj.put("CertificationDetails", allCertificationDetails);
		detailsObj.put("ProfessionalDetails", allProfessionalDetails);
		detailsObj.put("EducationDetails", allEducationDetails);
		detailsObj.put("ProjecDetails", allProjectDetails);
		detailsArray.add(detailsObj);
		
		return detailsArray;
	}

}
