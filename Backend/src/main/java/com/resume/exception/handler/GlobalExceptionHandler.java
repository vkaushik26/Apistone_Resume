package com.resume.exception.handler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.resume.dto.ApiResponse;
import com.resume.dto.ExceptionResponse;
import com.resume.exception.EmailIdDonotExistException;
import com.resume.exception.EmailIdExistException;

/**
 * 
 * @author PoojaKTanganiya
 * Global Exception Handler, handle the type of exceptions which are thrown and the 
 * corresponding method will be able to handle the exceptions.
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<ExceptionResponse>> handleNoSuchElementException(NoSuchElementException e)
	{
		return new ResponseEntity<ApiResponse<ExceptionResponse>>( new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), 
				new ExceptionResponse(e.getClass().getName(), "No such element is present in DB")), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmailIdExistException.class)
	public ResponseEntity<ApiResponse<ExceptionResponse>> handleEmailIdExistException(EmailIdExistException e)
	{
		return new ResponseEntity<ApiResponse<ExceptionResponse>>( new ApiResponse<>(HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), 
				new ExceptionResponse(e.getClass().getName(), "Email Id already exists in DB")), HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(EmailIdDonotExistException.class)
	public ResponseEntity<ApiResponse<ExceptionResponse>> handleEmailIdDonotExistException(EmailIdDonotExistException e)
	{
		return new ResponseEntity<ApiResponse<ExceptionResponse>>( new ApiResponse<>(HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), 
				new ExceptionResponse(e.getClass().getName(), "Email Id does not exist in our records")), HttpStatus.CONFLICT);
		
	}


}
