package com.bougouri.timetable.business.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public BusinessService() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Override
	public void registerProfessional(final Professional professional) throws BusinessException {
		validateEntity(professional);
		ensureWorkingDaysConsistency(professional.getWorkingDays());
		daoService.save(professional);
	}

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

	@Override
	public void makeAppointment(final long professionalId, final Appointment appointment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> getOnGoingAppointments(final long professionalId, final LocalDateTime referenceDate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void scheduleHoliday(final long professionalId, final Holiday holiday) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Holiday> getOnGoingHolidays(final long professionalId, final LocalDateTime referenceDate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	private Professional findProfessional(final long professionalId) throws BusinessException {
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
			if (previousTimeSlot != null && timeSlot.getEndTime().isAfter(previousTimeSlot.getStartTime())) {
				throw new BusinessException(ExceptionType.TIME_SLOT_OVERLAP, "At least 2 time slot overlap");
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

	// public static void main(final String[] args) throws BusinessException {
	// final BusinessService service = new BusinessService();
	// final Professional professional = new Professional("sdi", "pwd", "KONE", "Seydou", "");
	// final Holiday holiday = new Holiday();
	// holiday.setStartDateTime(LocalDateTime.of(2017, 1, 1, 1, 0));
	// professional.getHolidays().add(holiday);
	// service.validateEntity(professional).forEach(c -> System.out.println(c.getLeafBean().getClass().getSimpleName() + " - " + c.getPropertyPath() + " - " + c.getMessage()));
	// }

}
