package com.pioneer.dips.appointment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.appointment.model.Appointment;

@Repository
public interface appointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "SELECT * FROM appointment WHERE appointment.appointment_on = :date AND appointment.physician_id = :pid", nativeQuery = true)
	public List<Appointment> findByDate(@Param("date")Date date, @Param("pid")Long pid );
	
	@Query(value = "SELECT * FROM appointment WHERE appointment.userac_id = :id", nativeQuery = true)
	public List<Appointment> findByUser(@Param("id")Long id);

}
