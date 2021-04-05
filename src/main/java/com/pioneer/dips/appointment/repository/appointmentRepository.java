package com.pioneer.dips.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.symptoms.model.Symptom;

@Repository
public interface appointmentRepository extends JpaRepository<Appointment, Long> {

}
