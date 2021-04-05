package com.pioneer.dips.disease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.disease.model.Disease;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface diseaseRepository extends JpaRepository<Disease, Long> {

}
