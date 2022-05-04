package com.resume.dto;

/**
 * 
 * @author
 * This class attributes will be returned to the client as a part of customized api response.
 */
public class ApiResponse<T> {
	
	private Integer code;
	private String description;
	private T response;
	
	public ApiResponse() {
	}
	public ApiResponse(Integer code, String description, T response) {
		super();
		this.code = code;
		this.description = description;
		this.response = response;
	}
	
	public ApiResponse(Integer code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}

}

