package com.resume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resume.model.ProfessionalDetails;

public interface ProfessionalDetailsRepo extends JpaRepository<ProfessionalDetails, Object> {
	
	@Query(value="Select *from professional_details where user_id = :id", nativeQuery=true)
	List<ProfessionalDetails> findProfessionalDetailsById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value="Delete from professional_details where user_id = :id", nativeQuery=true)
	void deleteProfessionalDetailsById(@Param("id") int id);

}
