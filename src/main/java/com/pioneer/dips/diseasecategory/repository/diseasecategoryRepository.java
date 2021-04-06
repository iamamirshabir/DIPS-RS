package com.pioneer.dips.diseasecategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.diseasecategory.model.Diseasecategory;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface diseasecategoryRepository extends JpaRepository<Diseasecategory, Long> {

}
