package com.resume.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.model.ProjectsDetails;
import com.resume.repository.ProjectsDetailRepo;

@Service
public class ProjectsDetailService {
	@Autowired
	ProjectsDetailRepo projectsDetailRepo;

	/**
	 * 
	 * @param userId
	 * @return all projects details associated with particular user
	 */
	public List<ProjectsDetails> getProjectsDetailsById(int userId){

		List<ProjectsDetails> allProjectsDetails = projectsDetailRepo.findProjectsDetailsById(userId);


		return allProjectsDetails;
	}

	public ProjectsDetails createProjectsDetails(ProjectsDetails projectsDetails) {

		return projectsDetailRepo.save(projectsDetails);
	}

	/**
	 * 
	 * @param jsonObject, request body from frontend
	 * @return, save multiple records for in projects table for a particular user
	 * @throws ParseException
	 */

	public List<ProjectsDetails> saveProjectDetails(JSONObject jsonObject) throws ParseException{

		int userId = (int)jsonObject.get("userId");
		List<ProjectsDetails> projectList = new ArrayList<>();
		List<ProjectsDetails> allProjectDetails = this.getProjectsDetailsById(userId);

		if(allProjectDetails.size() > 0) {

			projectsDetailRepo.deleteProjectDetailsById(userId);
		}

		ArrayList projectsArray =  (ArrayList<ProjectsDetails>)jsonObject.get("projectdetails");

		// Iterate through each json object and set the values in ProjectDetails object
		// Insert all project detail records in database
		for(int i=0; i < projectsArray.size(); i++) {

			ProjectsDetails projectDetail = new ProjectsDetails();

			LinkedHashMap<String, Object> projectObject = (LinkedHashMap) projectsArray.get(i);

			String startDate = (String) projectObject.get("startDate");  
			String endDate = (String) projectObject.get("endDate");   
			String description = (String) projectObject.get("description");   
//			System.out.println(projectObject.get("projectId").getClass().getSimpleName());
			int id = (int)projectObject.get("projectId");   
			String stack = (String) projectObject.get("stack");   
			String clientname = (String) projectObject.get("clientName"); 
			String projectname = (String) projectObject.get("projectName");   
			System.out.println("isExpire :" + projectObject.get("expire").getClass().getSimpleName());

			
			if(projectObject.get("expire") instanceof Boolean) {
				boolean isExpire = (boolean) projectObject.get("expire");
				projectDetail.setExpire(isExpire);
			}else {
				if(projectObject.get("expire") instanceof String)
				{
					String isExpire = (String)projectObject.get("expire"); 
					if(isExpire.length()==0) {
						boolean _isExpire = false;
						projectDetail.setExpire(_isExpire);
					}

				}
			}

			projectDetail.setId(id);
			projectDetail.setStartDate(startDate);
			projectDetail.setEndDate(endDate);
			projectDetail.setDescription(description);
			projectDetail.setStack(stack);
			projectDetail.setClientName(clientname);
			projectDetail.setProjectName(projectname);
			projectDetail.setUserId(userId);

			projectList.add(projectDetail);
		} 



		return projectsDetailRepo.saveAll(projectList);

	}

	public ProjectsDetails updateProjectsDetails(String costCenterId, ProjectsDetails projectsDetails) {

		Optional<ProjectsDetails> projectsDetailsOptional = projectsDetailRepo.findById(costCenterId);

		if(!projectsDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		ProjectsDetails updateProjectsDetailsOptional = projectsDetailsOptional.get();

		updateProjectsDetailsOptional.setClientName(projectsDetails.getClientName());
		updateProjectsDetailsOptional.setDescription(projectsDetails.getDescription());
		updateProjectsDetailsOptional.setEndDate(projectsDetails.getEndDate());
		updateProjectsDetailsOptional.setProjectName(projectsDetails.getProjectName());
		updateProjectsDetailsOptional.setStartDate(projectsDetails.getStartDate());
		updateProjectsDetailsOptional.setStack(projectsDetails.getStack());
		updateProjectsDetailsOptional.setUserId(projectsDetails.getUserId());

		return projectsDetailRepo.save(updateProjectsDetailsOptional);
	}


	public void deleteProjectDetails(String costCenterId) {

		Optional<ProjectsDetails> ProjectsDetailsOptional = projectsDetailRepo.findById(costCenterId);

		if(!ProjectsDetailsOptional.isPresent()) {
			throw new NoSuchElementException();
		}

		projectsDetailRepo.deleteById(costCenterId);			

	}


}
