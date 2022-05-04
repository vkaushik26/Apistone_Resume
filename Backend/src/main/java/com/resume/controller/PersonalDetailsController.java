package com.resume.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resume.dto.ApiResponse;
import com.resume.dto.UserDetailResponse;
import com.resume.dto.UsersDetailResponse;
import com.resume.model.PersonalDetails;
import com.resume.service.PersonalDetailsService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class PersonalDetailsController {

	@Autowired
	PersonalDetailsService personalDetailsService;
			
	@GetMapping("/user/getAllUsersData")
	public ResponseEntity<ApiResponse<UsersDetailResponse>> getAllUsersData(){
		
		List<PersonalDetails> allUserDetails = personalDetailsService.getAllUsers();
		
		ApiResponse<UsersDetailResponse> apiResponse = new ApiResponse<>(200, "Success",
				new UsersDetailResponse(allUserDetails));

		return new ResponseEntity<ApiResponse<UsersDetailResponse>>(apiResponse, HttpStatus.OK);

	}
	
	@GetMapping("/user/id/{userId}")
	public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(@PathVariable("userId") int userId){
		
		PersonalDetails userDetails = personalDetailsService.getUserById(userId);	

		ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>(200, "Success",
				new UserDetailResponse(userDetails));

		return new ResponseEntity<ApiResponse<UserDetailResponse>>(apiResponse, HttpStatus.OK);

	}
	
	@GetMapping("/user/email/{emailId}")
	public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(@PathVariable("emailId") String emailId){

		PersonalDetails user = personalDetailsService.getUserByEmailId(emailId);
		ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>(200, "Success",
				new UserDetailResponse(user));

		return new ResponseEntity<ApiResponse<UserDetailResponse>>(apiResponse, HttpStatus.OK);

	}	
		
	@PostMapping("/user/credentials")
	public ResponseEntity<?> getUserCredentials(@RequestBody PersonalDetails personalDetails) throws Exception{

		JSONObject userCredential = personalDetailsService.userCredentials(personalDetails);

		return new ResponseEntity(userCredential, HttpStatus.OK);

	}	

	@PostMapping("/user")
	public ResponseEntity<ApiResponse<UserDetailResponse>> createUser(@RequestBody PersonalDetails personalDetails) throws Exception {

		JSONObject userDetails = personalDetailsService.createUser(personalDetails);

		return new ResponseEntity(userDetails, HttpStatus.CREATED);
	}

	@PutMapping("/user")
	public ResponseEntity<ApiResponse<UserDetailResponse>> updateUser(@RequestBody PersonalDetails personalDetails) throws Exception {

		PersonalDetails userDetails = personalDetailsService.updateUser(personalDetails);
		PersonalDetails updatedUserDetails = personalDetailsService.getUserDetail(userDetails);
		ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Updated Successfully",
				new UserDetailResponse(updatedUserDetails));

		return new ResponseEntity<ApiResponse<UserDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ApiResponse<UserDetailResponse>> deletePersonalDetailsById(@PathVariable("id") int id)
	{	
		 personalDetailsService.deleteUser(id);
		 ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>(200, "Resource Deleted Successfully");

		 return new ResponseEntity<ApiResponse<UserDetailResponse>>(apiResponse, HttpStatus.OK);
	}

}
