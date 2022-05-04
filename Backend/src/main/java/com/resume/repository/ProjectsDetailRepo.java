package com.resume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resume.model.ProjectsDetails;

public interface ProjectsDetailRepo extends JpaRepository<ProjectsDetails, Object> {

	@Query(value="Select *from projects where user_id = :id", nativeQuery=true)
	List<ProjectsDetails> findProjectsDetailsById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value="Delete from projects where user_id = :id", nativeQuery=true)
	void deleteProjectDetailsById(@Param("id") int id);

}
