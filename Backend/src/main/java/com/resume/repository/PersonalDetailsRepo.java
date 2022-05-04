package com.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resume.model.PersonalDetails;

@Repository
public interface PersonalDetailsRepo extends JpaRepository<PersonalDetails, Integer> {
		
	@Query("select p from PersonalDetails p where p.emailId= :emailId")
	public PersonalDetails findUserByEmailId(String emailId);

}
