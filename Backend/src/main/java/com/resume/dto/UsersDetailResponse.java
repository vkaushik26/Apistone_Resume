package com.resume.dto;

import java.util.List;

import com.resume.model.PersonalDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of UserDetails(list of Users details) response.
 */
public class UsersDetailResponse {
	
	private List<PersonalDetails> personalDetails;
	
	public UsersDetailResponse(List<PersonalDetails> personalDetails) {
		super();
		this.personalDetails = personalDetails;
	}

	public UsersDetailResponse() {

	}
	
	public List<PersonalDetails> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetails> personalDetails) {
		this.personalDetails = personalDetails;
	}

}
