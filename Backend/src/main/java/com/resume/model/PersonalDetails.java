package com.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="personal_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDetails {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="contact_number")
	private long contactNumber;
	
	@Column(name="email_id", unique=true)
	@NotNull
	private String emailId;
	
	@Column(name="address")
	private String address;
	
	@Column(name="linked_in_id")
	private String linkedInId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	public PersonalDetails() {

	}

	public PersonalDetails(@NotNull int userId, @NotNull String password, String firstName, String lastName, String dob,
			long contactNumber, @NotNull String emailId, String address, String linkedInId) {
		super();
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.address = address;
		this.linkedInId = linkedInId;
	}

	public PersonalDetails(@NotNull int userId, String firstName, String lastName, String dob, long contactNumber,
			@NotNull String emailId, String address, String linkedInId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.address = address;
		this.linkedInId = linkedInId;
	}

	@Override
	public String toString() {
		return "PersonalDetails [userId=" + userId + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + ", contactNumber=" + contactNumber + ", emailId="
				+ emailId + ", address=" + address + ", linkedInId=" + linkedInId + "]";
	}

	
}
