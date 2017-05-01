package com.bougouri.timetable.business.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Holiday;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.WorkingDay;
import com.bougouri.timetable.business.service.Exception.BusinessException;

public interface IBusinessService {

	void registerProfessional(Professional professional) throws BusinessException;

	void defineWorkingDays(long professionalId, List<WorkingDay> workingDays) throws BusinessException;

	void makeAppointment(long professionalId, Appointment appointment, LocalDateTime curentDate) throws BusinessException;

	List<Appointment> getOnGoingAppointments(long professionalId, LocalDateTime curentDate) throws BusinessException;

	void scheduleHoliday(long professionalId, Holiday holiday, LocalDateTime curentDate) throws BusinessException;

	List<Holiday> getOnGoingHolidays(long professionalId, LocalDateTime curentDate) throws BusinessException;
}
