package com.pioneer.dips.physician.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface physicianRepository extends JpaRepository<Physician, Long> {

}
