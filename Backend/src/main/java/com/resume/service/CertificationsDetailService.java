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

import com.resume.exception.NoDetailsFoundException;
import com.resume.model.CertificationsDetail;
import com.resume.model.EducationDetails;
import com.resume.repository.CertificationsDetailRepo;

@Service
public class CertificationsDetailService {
	
	@Autowired
	CertificationsDetailRepo certificationsDetailRepo;
	
	/**
	 * 
	 * @param userId, use to get the user by userId
	 * @return all certification details associated with particular user
	 */
	public List<CertificationsDetail> getCertificationsDetailById(int userId){
		
		List<CertificationsDetail> allCertificationDetails = certificationsDetailRepo.findCertificationDetailsById(userId);

		
		return allCertificationDetails;
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @return, save multiple records for in certification_detail table for particular user
	 * @throws ParseException
	 */
	
	public List<CertificationsDetail> saveCertificationDetails(JSONObject jsonObject) throws ParseException{
		
		int userId = (int)jsonObject.get("userId");
		List<CertificationsDetail> certificationList = new ArrayList<>();
		List<CertificationsDetail> allCertificationDetails = this.getCertificationsDetailById(userId);

		if(allCertificationDetails.size() > 0) {

			certificationsDetailRepo.deleteCertificationDetailsById(userId);
		}

		ArrayList certificationArray =  (ArrayList)jsonObject.get("certifications");

		// Iterate through each json object and set the values in CertificationDetails object
		// Insert all certification detail records in database
		for(int i=0; i < certificationArray.size(); i++) {

			CertificationsDetail certificationDetailObj = new CertificationsDetail();

			LinkedHashMap certificationObject = (LinkedHashMap) certificationArray.get(i);

			int certificateId = (int) certificationObject.get("certificateId");  
			String certificateName = (String) certificationObject.get("certificateName");   
			String issuer = (String) certificationObject.get("issuer");   
  
			String issueDate = (String) certificationObject.get("issueDate");   
			String expiryDate = (String) certificationObject.get("expiryDate"); 
			String credentialId = (String) certificationObject.get("credentialId");   
			String credentialUrl = (String) certificationObject.get("credentialUrl"); 
			
			if(certificationObject.get("expire") instanceof Boolean) {
				boolean isExpire = (boolean)certificationObject.get("expire");  
				certificationDetailObj.setExpire(isExpire);
			}else {
				if(certificationObject.get("expire") instanceof String)
				{
					String isExpire = (String)certificationObject.get("expire"); 
					if(isExpire.length()==0) {
						boolean _isExpire = false;
						certificationDetailObj.setExpire(_isExpire);
					}

				}
			}
			
			certificationDetailObj.setCertificateId(certificateId);
			certificationDetailObj.setCertificateName(certificateName);
			certificationDetailObj.setIssuer(issuer);
			certificationDetailObj.setIssueDate(issueDate);
			certificationDetailObj.setExpiryDate(expiryDate);
			certificationDetailObj.setCredentialId(credentialId);
			certificationDetailObj.setCredentialUrl(credentialUrl);
			certificationDetailObj.setUserId(userId);

			certificationList.add(certificationDetailObj);
		} 

		return certificationsDetailRepo.saveAll(certificationList);

	}
	
	
	public CertificationsDetail createCertificationDetails(CertificationsDetail certificationsDetail) {
		
		return certificationsDetailRepo.save(certificationsDetail);
	}
	
	public CertificationsDetail updateEducationDetails(String certificateId, CertificationsDetail certificationsDetail) {
		
		Optional<CertificationsDetail> certificationsDetailOptional = certificationsDetailRepo.findById(certificateId);
		
		if(!certificationsDetailOptional.isPresent()) {
			throw new NoSuchElementException();
		}
		
		CertificationsDetail updateCertificationsDetail = certificationsDetailOptional.get();
		
		updateCertificationsDetail.setCertificateName(certificationsDetail.getCertificateName());
		updateCertificationsDetail.setCredentialId(certificationsDetail.getCredentialId());
		updateCertificationsDetail.setCredentialUrl(certificationsDetail.getCredentialUrl());
		updateCertificationsDetail.setExpiryDate(certificationsDetail.getExpiryDate());
		updateCertificationsDetail.setExpire(certificationsDetail.isExpire());
		updateCertificationsDetail.setIssueDate(certificationsDetail.getIssueDate());
		updateCertificationsDetail.setIssuer(certificationsDetail.getIssuer());
		updateCertificationsDetail.setUserId(certificationsDetail.getUserId());
		
		return certificationsDetailRepo.save(updateCertificationsDetail);
	}
	

	public void deleteCertificationDetail(String certificateId) {

		Optional<CertificationsDetail> certificationsDetailOptional = certificationsDetailRepo.findById(certificateId);

		if(!certificationsDetailOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		certificationsDetailRepo.deleteById(certificateId);			

	}

}
