package com.pioneer.dips.disease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.disease.model.Disease;
@Repository
public interface diseaseRepository extends JpaRepository<Disease, Long> {

	@Query(value = "SELECT * FROM disease WHERE disease.disease_name LIKE %:name% FETCH FIRST ROW ONLY", nativeQuery = true)
	public Optional<Disease> findByName(@Param("name")String email);
	
}
