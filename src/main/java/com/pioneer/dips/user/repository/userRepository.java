package com.pioneer.dips.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.symptoms.model.Symptom;
import com.pioneer.dips.user.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

}
