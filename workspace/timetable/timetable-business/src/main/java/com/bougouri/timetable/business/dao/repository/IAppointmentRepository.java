package com.bougouri.timetable.business.dao.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Professional;

public interface IAppointmentRepository extends IBasicRepository<Appointment, Long> {
	
	@Query("select a from Appointment a where a.professional = :professional and a.date >= :date")
	List<Appointment> getAppointmentsStartingFrom(@Param("date") LocalDateTime date, @Param("professional") Professional professional);

	@Query("select a from Appointment a where a.professional = :professional and a.date = :date")
	Optional<Appointment> findAppointment(@Param("date") LocalDateTime date, @Param("professional") Professional professional);
}
