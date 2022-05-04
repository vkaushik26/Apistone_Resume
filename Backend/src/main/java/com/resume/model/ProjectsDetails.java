package com.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="projects")
public class ProjectsDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	
	@NotNull
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="client_name")
	private String clientName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="stack")
	private String stack;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="is_expire")
	private boolean expire;

	public ProjectsDetails() {
		
	}

	public ProjectsDetails(int projectId, @NotNull int id, int userId, String projectName, String clientName,
			String description, String stack, String startDate, String endDate, boolean expire) {
		super();
		this.projectId = projectId;
		this.id = id;
		this.userId = userId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.description = description;
		this.stack = stack;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expire = expire;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	
}
