package com.pioneer.dips.symptoms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface symptomRepository extends JpaRepository<Symptom, Long> {

}
