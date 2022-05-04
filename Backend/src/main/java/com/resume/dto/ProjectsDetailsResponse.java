package com.resume.dto;

import java.util.List;

import com.resume.model.ProjectsDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of projectsDetails(list of projects details) response.
 */
public class ProjectsDetailsResponse {
	
	private List<ProjectsDetails> projectsDetails;

	public ProjectsDetailsResponse(List<ProjectsDetails> projectsDetails) {
		super();
		this.projectsDetails = projectsDetails;
	}

	public ProjectsDetailsResponse() {
		
	}

	public List<ProjectsDetails> getProjectsDetails() {
		return projectsDetails;
	}

	public void setProjectsDetails(List<ProjectsDetails> projectsDetails) {
		this.projectsDetails = projectsDetails;
	}
	

}
