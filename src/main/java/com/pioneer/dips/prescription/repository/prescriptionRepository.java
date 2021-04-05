package com.pioneer.dips.prescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface prescriptionRepository extends JpaRepository<Prescription, Long> {

}
