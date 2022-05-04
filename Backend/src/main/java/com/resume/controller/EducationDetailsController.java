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
import com.resume.dto.EducationDetailResponse;
import com.resume.dto.EducationDetailsResponse;
import com.resume.model.EducationDetails;
import com.resume.service.EducationDetailsService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class EducationDetailsController {
	
	@Autowired
	EducationDetailsService educationDetailsService;
	
	@GetMapping("/educationDetails/{userId}")
	public ResponseEntity<ApiResponse<EducationDetailsResponse>> getEducationDetailsById(@PathVariable("userId") int userId){
		
		List<EducationDetails> allEducationDetails = educationDetailsService.getEducationDetailsById(userId);
		
		ApiResponse<EducationDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new EducationDetailsResponse(allEducationDetails));

		return new ResponseEntity<ApiResponse<EducationDetailsResponse>>(apiResponse, HttpStatus.OK);

	}
	
	@PostMapping("/educationDetails")
	public ResponseEntity<ApiResponse<EducationDetailResponse>> createProfessionalDetail(@RequestBody EducationDetails educationDetailsData) throws Exception {

		EducationDetails educationDetails = educationDetailsService.createEducationDetails(educationDetailsData);
		ApiResponse<EducationDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Created Successfully",
				new EducationDetailResponse(educationDetails));

		return new ResponseEntity<ApiResponse<EducationDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/educationDetails/{enrollmentId}")
	public ResponseEntity<ApiResponse<EducationDetailResponse>> updateProfessionalDetail(@PathVariable("enrollmentId") String enrollmentId,
										@RequestBody EducationDetails educationDetailsData) throws Exception {

		EducationDetails educationDetails = educationDetailsService.updateEducationDetails(enrollmentId, educationDetailsData);
		ApiResponse<EducationDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Updated Successfully",
				new EducationDetailResponse(educationDetails));

		return new ResponseEntity<ApiResponse<EducationDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/educationDetails/{enrollmentId}")
	public ResponseEntity<ApiResponse<EducationDetailResponse>> deleteEducationsDetailById(@PathVariable("enrollmentId") String enrollmentId)
	{	
		educationDetailsService.deleteEducationDetail(enrollmentId);
		ApiResponse<EducationDetailResponse> apiResponse = new ApiResponse<>(200, "Resource Deleted Successfully");

		return new ResponseEntity<ApiResponse<EducationDetailResponse>>(apiResponse, HttpStatus.OK);
	}
	
	@PostMapping("/allEducationDetails")
	public ResponseEntity<ApiResponse<EducationDetailsResponse>> saveEducationDetailsById(@RequestBody JSONObject jsonObject) throws ParseException{
		
		List<EducationDetails> allEducationDetails = educationDetailsService.saveEducationDetails(jsonObject);
		
		ApiResponse<EducationDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new EducationDetailsResponse(allEducationDetails));

		return new ResponseEntity<ApiResponse<EducationDetailsResponse>>(apiResponse, HttpStatus.OK);

	}

}
