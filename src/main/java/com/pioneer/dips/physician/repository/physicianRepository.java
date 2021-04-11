package com.pioneer.dips.physician.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.physician.model.Physician;

@Repository
public interface physicianRepository extends JpaRepository<Physician, Long> {

	@Query(value = "SELECT * FROM physician WHERE physician.physician_email LIKE %:email% FETCH FIRST ROW ONLY", nativeQuery = true)
	public Optional<Physician> findByEmail(@Param("email")String email);
	
}
