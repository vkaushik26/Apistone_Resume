package com.resume.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.exception.EmailIdDonotExistException;
import com.resume.exception.EmailIdExistException;
import com.resume.model.PersonalDetails;
import com.resume.repository.PersonalDetailsRepo;
import com.resume.utils.EncryptionAndDecryption;

@Service
public class PersonalDetailsService {

	@Autowired
	PersonalDetailsRepo personalDetailsRepo;

	public List<PersonalDetails> getAllUsers() {

		List<PersonalDetails> allUsers = personalDetailsRepo.findAll();
		
		List<PersonalDetails> allUserDetails = new ArrayList<>();
		
		for(PersonalDetails personalDetails : allUsers) {
			
			allUserDetails.add(this.getUserDetail(personalDetails));
		}

		return allUserDetails;
	}
	
	/**
	 * 
	 * @param userId, used to get user by userId.
	 * @return, Personal Details of a user
	 */
	public PersonalDetails getUserById(int userId) {

		Optional<PersonalDetails> userDetail = personalDetailsRepo.findById(userId);
		
		PersonalDetails userDetails = userDetail.get();
		
		return this.getUserDetail(userDetails);
		
	}
	
	/**
	 * 
	 * @return, all email id's from the database
	 */
	public List<String> getAllEmailIds() {
		
		List<PersonalDetails> allUsers = personalDetailsRepo.findAll();

		List<String> emailIdList = new ArrayList<>();

		for (PersonalDetails users : allUsers) {
			System.out.println(users.getEmailId().toLowerCase());
			emailIdList.add(users.getEmailId().toLowerCase());
		}

		return emailIdList;
	}
	
	/**
	 * 
	 * @param personalDetails
	 * @return, json object which will have user_id of a user.
	 * 			If email id already exists during registration, then it will throw an error, email id already exists.
	 * 			Encrypt the password , then save all personal details in db, if registration is successful, return user_id as response.
	 * @throws Exception
	 */
	public JSONObject createUser(PersonalDetails personalDetails) throws Exception {
		// Check if email id already exists, if so, then throw email id already exists

		List<String> emailIdList = getAllEmailIds();

		if (emailIdList.contains(personalDetails.getEmailId().toLowerCase())) {
			throw new EmailIdExistException();
		}

		EncryptionAndDecryption encryptDecryptObj = new EncryptionAndDecryption();

		// encrypt password
		String encodedPassword = encryptDecryptObj.encrypt(personalDetails.getPassword());
		personalDetails.setPassword(encodedPassword);
		personalDetailsRepo.save(personalDetails);
		JSONObject userCredentials = new JSONObject();
		userCredentials.put("user_id", personalDetails.getUserId());
		return userCredentials;
	}

	@SuppressWarnings("unchecked")
	public JSONObject userCredentials(PersonalDetails obj) throws Exception {
		// Check if email id already exists, if so, then throw email id already exists
		// Check if email id already exists, if so, then throw email id already exists

		List<String> emailIdList = getAllEmailIds();

		if (!emailIdList.contains(obj.getEmailId().toLowerCase())) {
			throw new EmailIdDonotExistException();
		}

		PersonalDetails user = personalDetailsRepo.findUserByEmailId(obj.getEmailId());

		EncryptionAndDecryption encryptDecryptObj = new EncryptionAndDecryption();
		String decodedPassword = encryptDecryptObj.decrypt(user.getPassword());
		if (obj.getPassword().equals(decodedPassword)) {
			JSONObject userCredentials = new JSONObject();
			userCredentials.put("user_id", user.getUserId());
			return userCredentials;
		} else {
			JSONObject userCredentials = new JSONObject();
			userCredentials.put("error", "Password Doesnot Match");
			return userCredentials;
		}
	}
	
	/**
	 * 
	 * @param personalDetails
	 * @return, updated fields for personal details.
	 * @throws Exception
	 */
	public PersonalDetails updateUser(PersonalDetails personalDetails) throws Exception {

		Optional<PersonalDetails> personalDetailsOptional = personalDetailsRepo.findById(personalDetails.getUserId());

		PersonalDetails updatedPersonalDetails = personalDetailsOptional.get();
		
		if(personalDetails.getFirstName()!=null) {
			updatedPersonalDetails.setFirstName(personalDetails.getFirstName());	
		}if(personalDetails.getLastName()!=null) {
			updatedPersonalDetails.setLastName(personalDetails.getLastName());
		}if(personalDetails.getAddress()!=null) {
			updatedPersonalDetails.setAddress(personalDetails.getAddress());
		}if(personalDetails.getEmailId()!=null) {
			updatedPersonalDetails.setEmailId(personalDetails.getEmailId());
		}if(personalDetails.getDob()!=null) {
			updatedPersonalDetails.setDob(personalDetails.getDob());
		}if(personalDetails.getLinkedInId()!=null) {
			updatedPersonalDetails.setLinkedInId(personalDetails.getLinkedInId());
		}if(personalDetails.getContactNumber() != 0) {
			updatedPersonalDetails.setContactNumber(personalDetails.getContactNumber());
		}

		return personalDetailsRepo.save(updatedPersonalDetails);

	}
	
	/**
	 * 
	 * @param id, delete user by user_id
	 */
	public void deleteUser(int id) {

		Optional<PersonalDetails> personalDetailsOptional = personalDetailsRepo.findById(id);

		if (!personalDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		personalDetailsRepo.deleteById(id);

	}
	
	/**
	 * 
	 * @param personalDetails
	 * @param id, use userId to find the user
	 * @return, personal details of a user
	 */
	public PersonalDetails getUserById(PersonalDetails personalDetails, int id) {

		Optional<PersonalDetails> userDetail = personalDetailsRepo.findById(id);
		if (userDetail.isPresent()) {
			
			PersonalDetails userDetails = userDetail.get();
			
			return this.getUserDetail(userDetails);
			
		} else {
			return null;
		}

	}
	
	/**
	 * 
	 * @param email_id, use emailId to get the user
	 * @return, personal details of a user
	 */
	public PersonalDetails getUserByEmailId(String email_id) {

		List<String> emailIdList = getAllEmailIds();

		if (!emailIdList.contains(email_id.toLowerCase())) {
			throw new NoSuchElementException();
		}

		PersonalDetails userDetails = personalDetailsRepo.findUserByEmailId(email_id);
			
		return this.getUserDetail(userDetails);

	}
	
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

}
