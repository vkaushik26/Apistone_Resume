package com.resume.dto;

import java.util.List;

import com.resume.model.EducationDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of EducationDetails(list of Education details) response.
 */
public class EducationDetailsResponse {
	
	private List<EducationDetails> educationDetails;

	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public EducationDetailsResponse(List<EducationDetails> educationDetails) {
		super();
		this.educationDetails = educationDetails;
	}

	public EducationDetailsResponse() {

	}
	
}
