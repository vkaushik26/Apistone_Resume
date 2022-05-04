package com.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="certifications")
public class CertificationsDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	
	@NotNull
	private int certificateId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="certificate_name")
	private String certificateName;
	
	@Column(name="issuer")
	private String issuer;
	
	@Column(name="is_expire")
	private boolean expire;
	
	@Column(name="issue_date")
	private String issueDate;
	
	@Column(name="expiry_date")
	private String expiryDate;
	
	@Column(name="credential_id")
	private String credentialId;
	
	@Column(name="credential_url")
	private String credentialUrl;

	public CertificationsDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CertificationsDetail(int cId, @NotNull int certificateId, int userId, String certificateName, String issuer,
			boolean expire, String issueDate, String expiryDate, String credentialId, String credentialUrl) {
		super();
		this.cId = cId;
		this.certificateId = certificateId;
		this.userId = userId;
		this.certificateName = certificateName;
		this.issuer = issuer;
		this.expire = expire;
		this.issueDate = issueDate;
		this.expiryDate = expiryDate;
		this.credentialId = credentialId;
		this.credentialUrl = credentialUrl;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(String credentialId) {
		this.credentialId = credentialId;
	}

	public String getCredentialUrl() {
		return credentialUrl;
	}

	public void setCredentialUrl(String credentialUrl) {
		this.credentialUrl = credentialUrl;
	}

}
