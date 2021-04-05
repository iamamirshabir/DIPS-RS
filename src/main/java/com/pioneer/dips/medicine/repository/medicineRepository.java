package com.pioneer.dips.medicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.medicine.model.Medicine;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface medicineRepository extends JpaRepository<Medicine, Long> {

}
