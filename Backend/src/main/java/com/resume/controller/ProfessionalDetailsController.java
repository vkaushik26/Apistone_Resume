package com.resume.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
import com.resume.dto.ProfessionalDetailResponse;
import com.resume.dto.ProfessionalDetailsResponse;
import com.resume.dto.UserDetailResponse;
import com.resume.model.ProfessionalDetails;
import com.resume.service.ProfessionalDetailsService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class ProfessionalDetailsController {
	
	@Autowired
	ProfessionalDetailsService professionalService;
	
	@GetMapping("/professionalDetails/{userId}")
	public ResponseEntity<ApiResponse<ProfessionalDetailsResponse>> getProfessionalDetailsById(@PathVariable("userId") int userId){
		
		List<ProfessionalDetails> allProfessionalDetails = professionalService.getProfessionalDetailsById(userId);
		
		ApiResponse<ProfessionalDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new ProfessionalDetailsResponse(allProfessionalDetails));

		return new ResponseEntity<ApiResponse<ProfessionalDetailsResponse>>(apiResponse, HttpStatus.OK);

	}
	
	@PostMapping("/professionalDetails")
	public ResponseEntity<ApiResponse<ProfessionalDetailResponse>> createProfessionalDetail(@RequestBody ProfessionalDetails professionalDetailsData) throws Exception {

		ProfessionalDetails professionalDetails = professionalService.createProfessionalDetails(professionalDetailsData);
		ApiResponse<ProfessionalDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Created Successfully",
				new ProfessionalDetailResponse(professionalDetails));

		return new ResponseEntity<ApiResponse<ProfessionalDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/professionalDetails/{pId}")
	public ResponseEntity<ApiResponse<ProfessionalDetailResponse>> updateProfessionalDetail(@PathVariable("pId") String pId,
										@RequestBody ProfessionalDetails professionalDetailsData) throws Exception {

		ProfessionalDetails professionalDetails = professionalService.updateProfessionalDetails(pId, professionalDetailsData);
		ApiResponse<ProfessionalDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Updated Successfully",
				new ProfessionalDetailResponse(professionalDetails));

		return new ResponseEntity<ApiResponse<ProfessionalDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/professionalDetails/{pId}")
	public ResponseEntity<ApiResponse<UserDetailResponse>> deleteProfessionalDetailsById(@PathVariable("pId") String pId)
	{	
		professionalService.deleteProfessionalDetail(pId);
		ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>(200, "Resource Deleted Successfully");

		return new ResponseEntity<ApiResponse<UserDetailResponse>>(apiResponse, HttpStatus.OK);
	}
	
	@PostMapping("/allProfessionalDetails")
	public ResponseEntity<ApiResponse<ProfessionalDetailsResponse>> saveProfessionalDetailsById(@RequestBody JSONObject jsonObject) throws ParseException{
		
		List<ProfessionalDetails> allProfessionalDetails = professionalService.saveProfessionalDetails(jsonObject);
		
		ApiResponse<ProfessionalDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new ProfessionalDetailsResponse(allProfessionalDetails));

		return new ResponseEntity<ApiResponse<ProfessionalDetailsResponse>>(apiResponse, HttpStatus.OK);

	}
}
