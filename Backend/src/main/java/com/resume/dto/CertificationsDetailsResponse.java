package com.resume.dto;

import java.util.List;

import com.resume.model.CertificationsDetail;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of Certifications Details(list of certifications) response.
 */
public class CertificationsDetailsResponse {
	
	private List<CertificationsDetail> certificationsDetail;

	public List<CertificationsDetail> getCertificationsDetail() {
		return certificationsDetail;
	}

	public void setCertificationsDetail(List<CertificationsDetail> certificationsDetail) {
		this.certificationsDetail = certificationsDetail;
	}

	public CertificationsDetailsResponse(List<CertificationsDetail> certificationsDetail) {
		super();
		this.certificationsDetail = certificationsDetail;
	}

	public CertificationsDetailsResponse() {

	}
	
}
