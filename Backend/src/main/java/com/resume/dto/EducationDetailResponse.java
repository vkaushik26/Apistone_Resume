package com.resume.dto;

import com.resume.model.EducationDetails;


/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of EducationDetails response.
 */
public class EducationDetailResponse {
	
	private EducationDetails educationDetails;

	public EducationDetailResponse(EducationDetails educationDetails) {
		super();
		this.educationDetails = educationDetails;
	}

	public EducationDetailResponse() {

	}

	public EducationDetails getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(EducationDetails educationDetails) {
		this.educationDetails = educationDetails;
	}
	
}
