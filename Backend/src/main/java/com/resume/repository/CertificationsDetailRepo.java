package com.resume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.resume.model.CertificationsDetail;

public interface CertificationsDetailRepo extends JpaRepository<CertificationsDetail, Object> {

	@Query(value="Select *from certifications where user_id = :id", nativeQuery=true)
	List<CertificationsDetail> findCertificationDetailsById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value="Delete from certifications where user_id = :id", nativeQuery=true)
	void deleteCertificationDetailsById(@Param("id") int id);
	
}
