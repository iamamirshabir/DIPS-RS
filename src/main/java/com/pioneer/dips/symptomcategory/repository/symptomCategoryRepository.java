package com.pioneer.dips.symptomcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.symptomcategory.model.SymptomCategory;

@Repository
public interface symptomCategoryRepository extends JpaRepository<SymptomCategory, Long> {

}
