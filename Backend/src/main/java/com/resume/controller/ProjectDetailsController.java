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
import com.resume.dto.ProjectDetailsResponse;
import com.resume.dto.ProjectsDetailsResponse;
import com.resume.model.ProjectsDetails;
import com.resume.service.ProjectsDetailService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class ProjectDetailsController {
	
	@Autowired
	ProjectsDetailService projectsDetailService;
	
	@GetMapping("/projectDetails/{userId}")
	public ResponseEntity<ApiResponse<ProjectsDetailsResponse>> getProjectsDetailById(@PathVariable("userId") int userId){

		List<ProjectsDetails> allProjectsDetails = projectsDetailService.getProjectsDetailsById(userId);

		ApiResponse<ProjectsDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new ProjectsDetailsResponse(allProjectsDetails));

		return new ResponseEntity<ApiResponse<ProjectsDetailsResponse>>(apiResponse, HttpStatus.OK);

	}

	@PostMapping("/projectDetails")
	public ResponseEntity<ApiResponse<ProjectDetailsResponse>> createProjectDetails(@RequestBody ProjectsDetails projectsDetailsData) throws Exception {

		ProjectsDetails projectsDetails = projectsDetailService.createProjectsDetails(projectsDetailsData);
		ApiResponse<ProjectDetailsResponse> apiResponse = new ApiResponse<>(201, "Resource Created Successfully",
				new ProjectDetailsResponse(projectsDetails));

		return new ResponseEntity<ApiResponse<ProjectDetailsResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/projectDetails/{costCenterId}")
	public ResponseEntity<ApiResponse<ProjectDetailsResponse>> updateProfessionalDetail(@PathVariable("costCenterId") String costCenterId,
										@RequestBody ProjectsDetails projectsDetailsData) throws Exception {

		ProjectsDetails projectsDetails = projectsDetailService.updateProjectsDetails(costCenterId, projectsDetailsData);
		ApiResponse<ProjectDetailsResponse> apiResponse = new ApiResponse<>(201, "Resource Updated Successfully",
				new ProjectDetailsResponse(projectsDetails));

		return new ResponseEntity<ApiResponse<ProjectDetailsResponse>>(apiResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/projectDetails/{certificateId}")
	public ResponseEntity<ApiResponse<ProjectDetailsResponse>> deleteProjectDetailsById(@PathVariable("costCenterId") String costCenterId)
	{	
		projectsDetailService.deleteProjectDetails(costCenterId);
		ApiResponse<ProjectDetailsResponse> apiResponse = new ApiResponse<>(200, "Resource Deleted Successfully");

		return new ResponseEntity<ApiResponse<ProjectDetailsResponse>>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/allProjectDetails")
	public ResponseEntity<ApiResponse<ProjectsDetailsResponse>> saveEducationDetailsById(@RequestBody JSONObject jsonObject) throws ParseException{
		
		List<ProjectsDetails> allProjectDetails = projectsDetailService.saveProjectDetails(jsonObject);
		
		ApiResponse<ProjectsDetailsResponse> apiResponse = new ApiResponse<>(200, "Success",
				new ProjectsDetailsResponse(allProjectDetails));

		return new ResponseEntity<ApiResponse<ProjectsDetailsResponse>>(apiResponse, HttpStatus.OK);

	}

}
