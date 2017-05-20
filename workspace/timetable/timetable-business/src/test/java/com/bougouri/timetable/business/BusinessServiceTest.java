package com.bougouri.timetable.business;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Holiday;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.service.IBusinessService;
import com.bougouri.timetable.business.service.Exception.BusinessException;

public class BusinessServiceTest extends AbstractTest {
	
	@Autowired
	private IBusinessService businessService;
	
	@Test
	public void professionalRegistrationTest() {
		// Assert that there is no professional entity in the database
		Assertions.assertThat(daoService.getAll(Professional.class).size()).isEqualTo(0);
		
		// Create a new professional with basic properties settled
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "KONE", "Seydou", "Gynecologue", "");
		
		// Save the new entity into the database
		try {
			businessService.registerProfessional(professional);
		} catch (final BusinessException e) {
			Assert.fail(e.getMessage());
		}
		
		// Check that the professional entity is correctly saved in the database
		final List<Professional> professionalList = daoService.getAll(Professional.class);
		Assertions.assertThat(professionalList.size()).isEqualTo(1);
		
		// Check the login already exists exception
		final Professional professional2 = new Professional("sdi", "pwdpwdpwdpwd", "KONE", "Seydou", "Gynecologue", "");
		Assertions.assertThatThrownBy(() -> businessService.registerProfessional(professional2)).isInstanceOf(BusinessException.class).hasMessage("Login sdi already exist");
		
		final Professional professionalDb = professionalList.get(0);
		
		// Empty the login to test the bean validation
		professionalDb.setLogin(null);
		Assertions.assertThatThrownBy(() -> businessService.registerProfessional(professionalDb)).isInstanceOf(BusinessException.class).hasMessageContaining("login");
		
		// Reset the login
		professionalDb.setLogin("sdi2");
		
		professionalDb.getWorkingDays().addAll(createWorkingDays());
		
		// Add another working day to get time overlap issue
		professionalDb.getWorkingDays().get(0).getTimeSlots().add(new TimeSlot(LocalTime.of(8, 50), LocalTime.of(9, 20)));
		
		Assertions.assertThatThrownBy(() -> businessService.registerProfessional(professionalDb)).isInstanceOf(BusinessException.class).hasMessage("At least 2 time slots overlap");
	}
	
	@Test
	public void appointmentRegistrationTest() {
		// It is possibe to make an appointment
		final Professional professional = new Professional("semk", "Excellence", "Karim", "KABORE", "Biotech", "Cherche mais je ne sais pas s'il trouve");
		try {
			businessService.registerProfessional(professional);
		} catch (final BusinessException e1) {
			Assert.fail(e1.getMessage());
		}
		final Appointment appointment = new Appointment();
		appointment.setAddress("26 route de Galice 13100 Aix-en-Provence");
		appointment.setClient("Polonnaise");
		appointment.setDate(LocalDateTime.now().plusDays(1));
		appointment.setDuration(30);
		appointment.setEmail("jte@live.fr");
		appointment.setProfessional(professional);
		try {
			businessService.makeAppointment(professional.getId(), appointment, LocalDateTime.now());
		} catch (final BusinessException e) {
			Assert.fail(e.getMessage());
		}
		
		// Assume that 2 appointments cannot overlap
		final Appointment appointment2 = new Appointment();
		appointment2.setAddress("26 route de Galice 13100 Aix-en-Provence");
		appointment2.setClient("Polonnaise");
		appointment2.setDate(LocalDateTime.now().plusDays(1));
		appointment2.setDuration(30);
		appointment2.setEmail("jte@live.fr");
		appointment2.setProfessional(professional);
		Assertions.assertThatThrownBy(() -> businessService.makeAppointment(professional.getId(), appointment2, LocalDateTime.now())).isInstanceOf(BusinessException.class).hasMessage("The appointment overlaps another one");
		
		// Assume that 2 similar appointments can be booked if the professionals are differents.
		final Professional professional2 = new Professional("adja", "FemmeVoilée", "Adja", "SORE", "Médécin Généraliste", "Soigne les gens");
		try {
			businessService.registerProfessional(professional2);
		} catch (final BusinessException e1) {
			Assert.fail(e1.getMessage());
		}
		
		try {
			businessService.makeAppointment(professional2.getId(), appointment2, LocalDateTime.now());
		} catch (final BusinessException e) {
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void holidayRegistrationTest() {
		// register holiday
		final Professional professional = new Professional("mamou", "Vendeuse_", "Mamou", "SANOU", "Commerçante", "Vend des articles");
		
		try {
			businessService.registerProfessional(professional);
		} catch (final BusinessException e1) {
			Assert.fail(e1.getMessage());
		}
		
		final Holiday holiday = new Holiday(LocalDateTime.of(2017, 12, 25, 0, 0, 0), LocalDateTime.of(2017, 12, 25, 23, 59, 0));
		try {
			businessService.scheduleHoliday(professional.getId(), holiday, LocalDateTime.now());
		} catch (final BusinessException e) {
			Assert.fail(e.getMessage());
		}
		
		// Cannot register 2 holidays that overlap
		final Holiday holiday2 = new Holiday(LocalDateTime.of(2017, 12, 25, 0, 0, 0), LocalDateTime.of(2017, 12, 25, 23, 59, 0));
		Assertions.assertThatThrownBy(() -> businessService.scheduleHoliday(professional.getId(), holiday2, LocalDateTime.now())).isInstanceOf(BusinessException.class).hasMessage("The holiday overlaps another one");
	}
}
