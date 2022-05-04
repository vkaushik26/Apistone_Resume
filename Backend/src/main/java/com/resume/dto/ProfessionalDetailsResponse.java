package com.resume.dto;

import java.util.List;
import com.resume.model.ProfessionalDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of ProfessionalDetails(list of Professional details) response.
 */
public class ProfessionalDetailsResponse {
	
	private List<ProfessionalDetails> professionalDetails;

	public ProfessionalDetailsResponse(List<ProfessionalDetails> professionalDetails) {
		super();
		this.professionalDetails = professionalDetails;
	}

	public ProfessionalDetailsResponse() {

	}

	public List<ProfessionalDetails> getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(List<ProfessionalDetails> professionalDetails) {
		this.professionalDetails = professionalDetails;
	}
	

}
