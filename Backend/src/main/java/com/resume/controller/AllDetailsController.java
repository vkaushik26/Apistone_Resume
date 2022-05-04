package com.resume.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resume.service.AllDetailsService;

@RestController
@RequestMapping("api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class AllDetailsController {
	
	@Autowired
	AllDetailsService allDetailsService;
	
	@GetMapping("/allDetails/{userId}")
	public ResponseEntity<?> getAllDetailsOfUser(@PathVariable("userId") int userId){
		
		JSONArray allDetailsArray = allDetailsService.getAllDetails(userId);
		
		return new ResponseEntity(allDetailsArray, HttpStatus.OK);
		
	}

}
