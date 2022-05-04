package com.resume.dto;

import com.resume.model.CertificationsDetail;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of Certifications Detail response.
 */
public class CertificationsDetailResponse {
	
	private CertificationsDetail certificationsDetail;

	public CertificationsDetailResponse(CertificationsDetail certificationsDetail) {
		super();
		this.certificationsDetail = certificationsDetail;
	}

	public CertificationsDetailResponse() {

	}

	public CertificationsDetail getCertificationsDetail() {
		return certificationsDetail;
	}

	public void setCertificationsDetail(CertificationsDetail certificationsDetail) {
		this.certificationsDetail = certificationsDetail;
	}
	
}
