package com.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="professional_details")
public class ProfessionalDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int profId;
	
	@NotNull
	private int pId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="role")
	private String role;
	
	@Column(name="achievements")
	private String achievements;
	
	@Column(name="is_expire")
	private boolean expire;

	public ProfessionalDetails() {

	}

	public ProfessionalDetails(int profId, @NotNull int pId, int userId, String companyName, String startDate,
			String endDate, String role, String achievements, boolean expire) {
		super();
		this.profId = profId;
		this.pId = pId;
		this.userId = userId;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.role = role;
		this.achievements = achievements;
		this.expire = expire;
	}

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	
	
}
