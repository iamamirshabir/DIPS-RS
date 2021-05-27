package com.pioneer.dips.physician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.physician.model.Physician;

@Repository
public interface physicianRepository extends JpaRepository<Physician, Long> {

	@Query(value = "SELECT * FROM physician WHERE physician.physician_keycloak_id LIKE %:keycloak_id% FETCH FIRST ROW ONLY", nativeQuery = true)
	public Optional<Physician> findByKeycloakID(@Param("keycloak_id")String keycloak_id);
	
	@Query(value = "SELECT * FROM physician WHERE physician.physician_reg_status = true", nativeQuery = true)
	public List<Physician> findAllReg();
	
	@Query(value = "SELECT * FROM physician WHERE physician.physician_reg_status = false", nativeQuery = true)
	public List<Physician> findAllNotReg();
	
}
