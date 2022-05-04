package com.resume.dto;

import com.resume.model.ProjectsDetails;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of projectsDetails response.
 */
public class ProjectDetailsResponse {
	
	private ProjectsDetails projectsDetails;

	public ProjectDetailsResponse(ProjectsDetails projectsDetails) {
		super();
		this.projectsDetails = projectsDetails;
	}

	public ProjectDetailsResponse() {

	}

	public ProjectsDetails getProjectsDetails() {
		return projectsDetails;
	}

	public void setProjectsDetails(ProjectsDetails projectsDetails) {
		this.projectsDetails = projectsDetails;
	}
	

}
