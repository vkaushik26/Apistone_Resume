package com.resume.dto;

import com.resume.model.ProfessionalDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of ProfessionalDetails response.
 */
public class ProfessionalDetailResponse {
	
	private ProfessionalDetails professionalDetails;

	public ProfessionalDetailResponse() {

	}

	public ProfessionalDetailResponse(ProfessionalDetails professionalDetails) {
		super();
		this.professionalDetails = professionalDetails;
	}

	public ProfessionalDetails getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(ProfessionalDetails professionalDetails) {
		this.professionalDetails = professionalDetails;
	} 
	
	

}
