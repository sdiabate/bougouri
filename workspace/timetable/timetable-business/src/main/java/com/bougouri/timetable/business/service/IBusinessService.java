package com.bougouri.timetable.business.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Holiday;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.WorkingDay;
import com.bougouri.timetable.business.service.Exception.BusinessException;

public interface IBusinessService {
	
	Professional registerProfessional(Professional professional) throws BusinessException;
	
	List<WorkingDay> defineWorkingDays(long professionalId, List<WorkingDay> workingDays) throws BusinessException;
	
	Appointment makeAppointment(long professionalId, Appointment appointment, LocalDateTime curentDate) throws BusinessException;
	
	List<Appointment> getOnGoingAppointments(long professionalId, LocalDateTime curentDate) throws BusinessException;
	
	Holiday scheduleHoliday(long professionalId, Holiday holiday, LocalDateTime curentDate) throws BusinessException;
	
	List<Holiday> getOnGoingHolidays(long professionalId, LocalDateTime curentDate) throws BusinessException;
	
	Professional findProfessional(long professionalId) throws BusinessException;

	Optional<Holiday> cancelHoliday(long professionalId, LocalDateTime holidayStartDate) throws BusinessException;

	Optional<Appointment> cancelAppointment(long professionalId, LocalDateTime appointmentStartDate) throws BusinessException;
}
