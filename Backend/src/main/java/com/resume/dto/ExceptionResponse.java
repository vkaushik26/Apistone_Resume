package com.resume.dto;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of customized exception handling.
 */

public class ExceptionResponse {
	
	private String type;
	private String message;
	
	public ExceptionResponse(String type, String message) {
		super();
		this.type = type;
		this.message = message;
	}
	public ExceptionResponse() {
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
