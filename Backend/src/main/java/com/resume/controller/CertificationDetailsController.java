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
import com.resume.dto.CertificationsDetailResponse;
import com.resume.dto.CertificationsDetailsResponse;
import com.resume.dto.EducationDetailsResponse;
import com.resume.model.CertificationsDetail;
import com.resume.model.EducationDetails;
import com.resume.service.CertificationsDetailService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class CertificationDetailsController {
	
	@Autowired
	CertificationsDetailService certificationsDetailService;
	
	@GetMapping("/certificationDetails/{userId}")
	public ResponseEntity<ApiResponse<CertificationsDetailsResponse>> getEducationDetailsById(@PathVariable("userId") int userId){

		List<CertificationsDetail> allCertificationDetails = certificationsDetailService.getCertificationsDetailById(userId);

		ApiResponse<CertificationsDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new CertificationsDetailsResponse(allCertificationDetails));

		return new ResponseEntity<ApiResponse<CertificationsDetailsResponse>>(apiResponse, HttpStatus.OK);

	}

	@PostMapping("/certificationDetails")
	public ResponseEntity<ApiResponse<CertificationsDetailResponse>> createCertificationDetail(@RequestBody CertificationsDetail certificationsDetailData) throws Exception {

		CertificationsDetail certificationsDetail = certificationsDetailService.createCertificationDetails(certificationsDetailData);
		ApiResponse<CertificationsDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Created Successfully",
				new CertificationsDetailResponse(certificationsDetail));

		return new ResponseEntity<ApiResponse<CertificationsDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/certificationDetails/{certificateId}")
	public ResponseEntity<ApiResponse<CertificationsDetailResponse>> updateProfessionalDetail(@PathVariable("certificateId") String certificateId,
										@RequestBody CertificationsDetail certificationsDetailData) throws Exception {

		CertificationsDetail certificationsDetail = certificationsDetailService.updateEducationDetails(certificateId, certificationsDetailData);
		ApiResponse<CertificationsDetailResponse> apiResponse = new ApiResponse<>(201, "Resource Updated Successfully",
				new CertificationsDetailResponse(certificationsDetail));

		return new ResponseEntity<ApiResponse<CertificationsDetailResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/certificationDetails/{certificateId}")
	public ResponseEntity<ApiResponse<CertificationsDetailResponse>> deleteCertificationDetailById(@PathVariable("certificateId") String certificateId)
	{	
		certificationsDetailService.deleteCertificationDetail(certificateId);
		ApiResponse<CertificationsDetailResponse> apiResponse = new ApiResponse<>(200, "Resource Deleted Successfully");

		return new ResponseEntity<ApiResponse<CertificationsDetailResponse>>(apiResponse, HttpStatus.OK);
	}
	
	@PostMapping("/allCertificationsDetails")
	public ResponseEntity<ApiResponse<CertificationsDetailsResponse>> saveEducationDetailsById(@RequestBody JSONObject jsonObject) throws ParseException{
		
		List<CertificationsDetail> allCertificationDetails = certificationsDetailService.saveCertificationDetails(jsonObject);
		
		ApiResponse<CertificationsDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new CertificationsDetailsResponse(allCertificationDetails));

		return new ResponseEntity<ApiResponse<CertificationsDetailsResponse>>(apiResponse, HttpStatus.OK);

	}

}
