package com.bougouri.timetable.business.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bougouri.timetable.business.dao.repository.IAppointmentRepository;
import com.bougouri.timetable.business.dao.repository.IUserRepository;
import com.bougouri.timetable.business.model.AbstractEntity;
import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Holiday;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.model.WorkingDay;
import com.bougouri.timetable.business.service.IBasicDaoService;
import com.bougouri.timetable.business.service.IBusinessService;
import com.bougouri.timetable.business.service.Exception.BusinessException;
import com.bougouri.timetable.business.service.Exception.ExceptionType;

@Service
public class BusinessService implements IBusinessService {
	
	private final Validator validator;
	
	@Autowired
	private IBasicDaoService daoService;
	
	@Autowired
	private IAppointmentRepository appointmentRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	public BusinessService() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Transactional
	@Override
	public void registerProfessional(final Professional professional) throws BusinessException {
		validateEntity(professional);

		// Check that the login is unique for new entities
		if (professional.getId() == null && userRepository.findFirstByLogin(professional.getLogin()).isPresent()) {
			throw new BusinessException(ExceptionType.LOGIN_ALREADY_EXISTS, String.format("Login %s already exist", professional.getLogin()));
		}

		ensureWorkingDaysConsistency(professional.getWorkingDays());

		daoService.save(professional);
	}
	
	@Transactional
	@Override
	public void defineWorkingDays(final long professionalId, final List<WorkingDay> workingDays) throws BusinessException {
		for (final WorkingDay workingDay : workingDays) {
			validateEntity(workingDay);
		}
		ensureWorkingDaysConsistency(workingDays);
		final Professional professional = findProfessional(professionalId);
		professional.getWorkingDays().clear();
		professional.getWorkingDays().addAll(workingDays);
		daoService.save(professional);
	}
	
	@Transactional
	@Override
	public void makeAppointment(final long professionalId, final Appointment appointment, final LocalDateTime curentDate) throws BusinessException {
		
		final Professional professional = findProfessional(professionalId);
		appointment.setProfessional(professional);
		
		validateEntity(appointment);
		
		// Check that the appointment is not in the past
		if (appointment.getDate().isBefore(curentDate)) {
			throw new BusinessException(ExceptionType.FUNCTIONAL, "The appointment is in the past");
		}
		
		final Interval appointmentInterval = new Interval(appointment.getDate().toEpochSecond(ZoneOffset.UTC), appointment.getDate().plusMinutes(appointment.getDuration()).toEpochSecond(ZoneOffset.UTC));
		
		// Check that the current appointment doesn't overlap with another
		for (final Appointment current : getOnGoingAppointments(professionalId, curentDate)) {
			final Interval currentInterval = new Interval(current.getDate().toEpochSecond(ZoneOffset.UTC), current.getDate().plusMinutes(current.getDuration()).toEpochSecond(ZoneOffset.UTC));
			if (appointmentInterval.overlaps(currentInterval)) {
				throw new BusinessException(ExceptionType.APPOINTMENT_OVERLAP, "The appointment overlaps another one");
			}
		}
		
		// Check that the current appointment doesn't overlap an holiday
		for (final Holiday holiday : getOnGoingHolidays(professionalId, curentDate)) {
			final Interval interval = new Interval(holiday.getStartDateTime().toEpochSecond(ZoneOffset.UTC), holiday.getEndDateTime().toEpochSecond(ZoneOffset.UTC));
			if (appointmentInterval.overlaps(interval)) {
				throw new BusinessException(ExceptionType.APPOINTMENT_ON_HOLIDAY, "The appointment overlaps an holiday");
			}
		}
		
		daoService.save(appointment);
	}
	
	@Override
	public List<Appointment> getOnGoingAppointments(final long professionalId, final LocalDateTime curentDate) throws BusinessException {
		return appointmentRepository.getAppointmentsStartingFrom(curentDate, findProfessional(professionalId));
	}
	
	@Transactional
	@Override
	public void scheduleHoliday(final long professionalId, final Holiday holiday, final LocalDateTime curentDate) throws BusinessException {
		
		validateEntity(holiday);
		
		final Professional professional = findProfessional(professionalId);
		
		final Interval holidayInterval = new Interval(holiday.getStartDateTime().toEpochSecond(ZoneOffset.UTC), holiday.getEndDateTime().toEpochSecond(ZoneOffset.UTC));
		
		// Check that the current holiday doesn't overlap another one
		for (final Holiday current : professional.getHolidays()) {
			final Interval interval = new Interval(current.getStartDateTime().toEpochSecond(ZoneOffset.UTC), current.getEndDateTime().toEpochSecond(ZoneOffset.UTC));
			if (holidayInterval.overlaps(interval)) {
				throw new BusinessException(ExceptionType.HOLIDAY_OVERLAP, "The holiday overlaps another one");
			}
		}
		
		// Check that the current holiday doen't overlap an appointment
		for (final Appointment appointment : getOnGoingAppointments(professionalId, curentDate)) {
			final Interval appointmentInterval = new Interval(appointment.getDate().toEpochSecond(ZoneOffset.UTC), appointment.getDate().plusMinutes(appointment.getDuration()).toEpochSecond(ZoneOffset.UTC));
			if (holidayInterval.overlaps(appointmentInterval)) {
				throw new BusinessException(ExceptionType.HOLIDAY_ON_APPOINTMENT, "The holiday overlaps an appointment");
			}
		}
		
		professional.getHolidays().add(holiday);
		
		daoService.save(professional);
	}
	
	@Override
	public List<Holiday> getOnGoingHolidays(final long professionalId, final LocalDateTime curentDate) throws BusinessException {
		return findProfessional(professionalId).getHolidays().stream().filter(h -> h.getStartDateTime().isAfter(curentDate)).collect(Collectors.toList());
	}
	
	@Override
	public Professional findProfessional(final long professionalId) throws BusinessException {
		final Optional<Professional> professional = daoService.find(Professional.class, professionalId);
		if (professional.isPresent()) {
			return professional.get();
		}
		throw new BusinessException(ExceptionType.MISSING_ENTITY, String.format("Professional entity with id %d not found", professionalId));
	}
	
	private void ensureWorkingDaysConsistency(final List<WorkingDay> workingDays) throws BusinessException {
		// Check that there is at most one working day per weekday
		if (workingDays.stream().map(WorkingDay::getWeekday).distinct().count() < workingDays.size()) {
			throw new BusinessException(ExceptionType.FUNCTIONAL, "At least 2 working days in the same weekday");
		}
		for (final WorkingDay workingDay : workingDays) {
			ensureTimeSlotConsistency(workingDay.getTimeSlots());
		}
	}
	
	private void ensureTimeSlotConsistency(final List<TimeSlot> timeSlots) throws BusinessException {
		timeSlots.sort(Comparator.comparing(TimeSlot::getStartTime));
		TimeSlot previousTimeSlot = null;
		for (final TimeSlot timeSlot : timeSlots) {
			// Check that the end time is after the start time
			if (!timeSlot.getEndTime().isAfter(timeSlot.getStartTime())) {
				throw new BusinessException(ExceptionType.FUNCTIONAL, "Time slot slot end time not greater than the start time");
			}
			if (previousTimeSlot != null && previousTimeSlot.getEndTime().isAfter(timeSlot.getStartTime())) {
				throw new BusinessException(ExceptionType.TIME_SLOT_OVERLAP, "At least 2 time slots overlap");
			}
			previousTimeSlot = timeSlot;
		}
	}
	
	private void validateEntity(final AbstractEntity entity) throws BusinessException {
		final Set<ConstraintViolation<AbstractEntity>> violations = new HashSet<>();
		try {
			cascadeValidateEntity(entity, violations);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new BusinessException(ExceptionType.TECHNICAL, e);
		}
		if (!violations.isEmpty()) {
			final StringBuilder stringBuilder = new StringBuilder();
			violations.forEach(v -> stringBuilder.append(String.format("%s - %s - %s\n", v.getLeafBean().getClass().getSimpleName(), v.getPropertyPath(), v.getMessage())));
			throw new BusinessException(ExceptionType.INVALID_PROPERTY_VALUE, stringBuilder.toString());
		}
	}
	
	private void cascadeValidateEntity(final AbstractEntity entity, final Set<ConstraintViolation<AbstractEntity>> violations) throws IllegalArgumentException, IllegalAccessException {
		// Validate the parent entity
		violations.addAll(validator.validate(entity));
		// Find the child entities and validate them
		for (final Field field : entity.getClass().getDeclaredFields()) {
			final boolean accessible = field.isAccessible();
			field.setAccessible(true);
			if (AbstractEntity.class.isAssignableFrom(field.getType())) {
				final Object childEntity = field.get(entity);
				if (childEntity != null) {
					cascadeValidateEntity((AbstractEntity) childEntity, violations);
				}
			} else if (Collection.class.isAssignableFrom(field.getType())) {
				final Type[] genericTypes = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
				if (AbstractEntity.class.isAssignableFrom((Class<?>) genericTypes[0])) {
					for (final Object childEntity : (Collection<?>) field.get(entity)) {
						cascadeValidateEntity((AbstractEntity) childEntity, violations);
					}
				}
			}
			
			// Manage the other case like Map
			
			field.setAccessible(accessible);
		}
	}
	
}
