package com.resume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resume.model.EducationDetails;

public interface EducationDetailsRepo extends JpaRepository<EducationDetails, Object> {
	
	@Query(value="Select *from education_details where user_id = :id", nativeQuery=true)
	List<EducationDetails> findEducationDetailsById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value="Delete from education_details where user_id = :id", nativeQuery=true)
	void deleteEducationDetailsById(@Param("id") int id);

}
