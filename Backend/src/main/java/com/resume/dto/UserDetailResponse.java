package com.resume.dto;

import java.util.List;

import com.resume.model.PersonalDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of UserDetails response.
 */
public class UserDetailResponse {
	
	private PersonalDetails personalDetails;

	public UserDetailResponse(PersonalDetails personalDetails) {
		super();
		this.personalDetails = personalDetails;
	}

	public UserDetailResponse() {
		
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}
	
}
