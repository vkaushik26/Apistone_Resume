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

import com.resume.model.PersonalDetails;
import com.resume.model.ProfessionalDetails;
import com.resume.repository.ProfessionalDetailsRepo;

@Service
public class ProfessionalDetailsService {

	@Autowired
	ProfessionalDetailsRepo professionalRepo;

	/**
	 * 
	 * @param userId
	 * @return all professional details associated with particular user
	 */
	public List<ProfessionalDetails> getProfessionalDetailsById(int userId){

		List<ProfessionalDetails> allProfessionalDetails = professionalRepo.findProfessionalDetailsById(userId);

		return allProfessionalDetails;
	}


	public ProfessionalDetails createProfessionalDetails(ProfessionalDetails professionalDetails) {

		return professionalRepo.save(professionalDetails);
	}

	/**
	 * 
	 * @param jsonObject, request body from frontend
	 * @return, save multiple records for in professional_details table for particular user
	 * @throws ParseException
	 */
	public List<ProfessionalDetails> saveProfessionalDetails(JSONObject jsonObject) throws ParseException{

		int userId = (int)jsonObject.get("userId");
		List<ProfessionalDetails> professionalList = new ArrayList<>();
		List<ProfessionalDetails> allProfessionalDetails = this.getProfessionalDetailsById(userId);
		
		if(allProfessionalDetails.size() > 0) {

			professionalRepo.deleteProfessionalDetailsById(userId);

		}
		ArrayList professionalArray =  (ArrayList)jsonObject.get("professional");

		// Iterate through each json object and set the values in ProfessionalDetails object
		// Insert all professional detail records in database
		for(int i=0; i < professionalArray.size(); i++) {

			ProfessionalDetails professionalDetail = new ProfessionalDetails();

			LinkedHashMap professionalObject = (LinkedHashMap) professionalArray.get(i);

			String startDate = (String) professionalObject.get("startDate");  
			String endDate = (String) professionalObject.get("endDate");   
			String role = (String) professionalObject.get("role");   
			int pid = (int)professionalObject.get("pId");   
			if(professionalObject.get("expire") instanceof Boolean) {
				boolean isExpire = (boolean) professionalObject.get("expire");   
				professionalDetail.setExpire(isExpire);
			}else {
				if(professionalObject.get("expire") instanceof String)
				{
					String isExpire = (String) professionalObject.get("expire");  
					if(isExpire.length()==0) {
						boolean _isExpire = false;
						professionalDetail.setExpire(_isExpire);
					}

				}
			}
			String achievements = (String) professionalObject.get("achievements"); 
			String companyName = (String)professionalObject.get("companyName");

			professionalDetail.setStartDate(startDate);
			professionalDetail.setEndDate(endDate);
			professionalDetail.setRole(role);
			professionalDetail.setCompanyName(companyName);
			professionalDetail.setAchievements(achievements);
			professionalDetail.setpId(pid);
			professionalDetail.setUserId(userId);

			professionalList.add(professionalDetail);
		} 


		return professionalRepo.saveAll(professionalList);

	}

	public ProfessionalDetails updateProfessionalDetails(String pId, ProfessionalDetails professionalDetails) {

		Optional<ProfessionalDetails> professionalDetailsOptional = professionalRepo.findById(pId);

		if(!professionalDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		ProfessionalDetails updateProfessionalDetails = professionalDetailsOptional.get();

		updateProfessionalDetails.setUserId(professionalDetails.getUserId());
		updateProfessionalDetails.setCompanyName(professionalDetails.getCompanyName());
		updateProfessionalDetails.setAchievements(professionalDetails.getAchievements());
		updateProfessionalDetails.setStartDate(professionalDetails.getStartDate());
		updateProfessionalDetails.setEndDate(professionalDetails.getEndDate());
		updateProfessionalDetails.setRole(professionalDetails.getRole());
		updateProfessionalDetails.setExpire(professionalDetails.isExpire());

		return professionalRepo.save(updateProfessionalDetails);
	}


	public void deleteProfessionalDetail(String pId) {

		Optional<ProfessionalDetails> ProfessionalDetailsOptional = professionalRepo.findById(pId);

		if(!ProfessionalDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		professionalRepo.deleteById(pId);			

	}



}
