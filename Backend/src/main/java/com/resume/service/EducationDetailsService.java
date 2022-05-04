package com.resume.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.model.EducationDetails;
import com.resume.repository.EducationDetailsRepo;

@Service
public class EducationDetailsService {

	@Autowired
	EducationDetailsRepo educationDetailsRepo;

	/**
	 * 
	 * @param userId
	 * @return all education details associated with particular user
	 */
	public List<EducationDetails> getEducationDetailsById(int userId){

		List<EducationDetails> allEducationDetails = educationDetailsRepo.findEducationDetailsById(userId);

		return allEducationDetails;
	}

	public EducationDetails createEducationDetails(EducationDetails educationDetails) {

		return educationDetailsRepo.save(educationDetails);
	}


	/**
	 * 
	 * @param jsonObject
	 * @return, save multiple records for in education_details table for particular user
	 * @throws ParseException
	 */

	public List<EducationDetails> saveEducationDetails(JSONObject jsonObject) throws ParseException{

		int userId = (int)jsonObject.get("userId");
		List<EducationDetails> educationList = new ArrayList<>();
		List<EducationDetails> allEducationDetails = this.getEducationDetailsById(userId);

		if(allEducationDetails.size() > 0) {

			educationDetailsRepo.deleteEducationDetailsById(userId);
		}

		ArrayList educationArray =  (ArrayList)jsonObject.get("education");

		// Iterate through each json object and set the values in EducationDetails object
		// Insert all education detail records in database
		for(int i=0; i < educationArray.size(); i++) {

			EducationDetails educationDetail = new EducationDetails();

			LinkedHashMap educationObject = (LinkedHashMap) educationArray.get(i);

			String fromDate = (String) educationObject.get("fromDate");  
			String toDate = (String) educationObject.get("toDate");   
			String specialization = (String) educationObject.get("specialization");    
			String university = (String) educationObject.get("university");   
			String achievements = (String) educationObject.get("achievements"); 

			if(educationObject.get("cgpa") instanceof Integer) {
				int cgpa = (int) educationObject.get("cgpa");
				educationDetail.setCgpa(cgpa);
			}else if(educationObject.get("cgpa") instanceof Double) {
					double cgpa = (double) educationObject.get("cgpa");
					educationDetail.setCgpa(cgpa);
			}else {
				String cgpaStr = (String) educationObject.get("cgpa");
				if(cgpaStr.length()==0) {
					cgpaStr = "0";
				}
				if(cgpaStr.contains(".")) {
					double cgpa = Double.parseDouble(cgpaStr);  
					educationDetail.setCgpa(cgpa);
				}else {
					int cgpa = Integer.parseInt(cgpaStr);  
					educationDetail.setCgpa(cgpa);
				}

			}

			educationDetail.setFromDate(fromDate);
			educationDetail.setToDate(toDate);
			educationDetail.setSpecialization(specialization);
			educationDetail.setUniversity(university);
			educationDetail.setAchievements(achievements);
			educationDetail.setUserId(userId);

			educationList.add(educationDetail);
		} 



		return educationDetailsRepo.saveAll(educationList);

	}

	public EducationDetails updateEducationDetails(String enrollmentId, EducationDetails educationDetails) {

		Optional<EducationDetails> educationDetailsOptional = educationDetailsRepo.findById(enrollmentId);

		if(!educationDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		EducationDetails updateEducationDetails = educationDetailsOptional.get();

		updateEducationDetails.setUserId(educationDetails.getUserId());
		updateEducationDetails.setFromDate(educationDetails.getFromDate());
		updateEducationDetails.setToDate(educationDetails.getToDate());
		updateEducationDetails.setUniversity(educationDetails.getUniversity());
		updateEducationDetails.setCgpa(educationDetails.getCgpa());
		updateEducationDetails.setAchievements(educationDetails.getAchievements());
		updateEducationDetails.setSpecialization(educationDetails.getSpecialization());

		return educationDetailsRepo.save(updateEducationDetails);
	}


	public void deleteEducationDetail(String enrollmentId) {

		Optional<EducationDetails> educationDetailsOptional = educationDetailsRepo.findById(enrollmentId);

		if(!educationDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		educationDetailsRepo.deleteById(enrollmentId);			

	}
}
