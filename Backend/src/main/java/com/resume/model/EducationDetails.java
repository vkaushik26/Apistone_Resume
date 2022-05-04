package com.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="education_details")
public class EducationDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	
	@NotNull
	private int enrollmentNo;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="university")
	private String university;
	
	@Column(name="cgpa")
	private double cgpa;
	
	@Column(name="specialization")
	private String specialization;

	@Column(name="from_date")
	private String fromDate;
	
	@Column(name="to_date")
	private String toDate;
	
	@Column(name="achievements")
	private String achievements;

	public EducationDetails() {

	}

	public EducationDetails(int eId, @NotNull int enrollmentNo, int userId, String university, double cgpa,
			String specialization, String fromDate, String toDate, String achievements) {
		super();
		this.eId = eId;
		this.enrollmentNo = enrollmentNo;
		this.userId = userId;
		this.university = university;
		this.cgpa = cgpa;
		this.specialization = specialization;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.achievements = achievements;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(int enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}


}
