package com.bougouri.timetable.business;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.model.Weekday;
import com.bougouri.timetable.business.model.WorkingDay;

public class DaoTest extends AbstractTest {

	@Test
	public void contextLoads() throws Exception {
		Assert.assertNotNull(daoService);
	}

	@Test
	public void persistenceTest() {
		// Assert that there is no professional entity in the database
		Assert.assertEquals(0, daoService.getAll(Professional.class).size());
		// Create a new professional with basic properties settled
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "KONE", "Seydou", "Gynecologue", "");
		// Save the create professional in the database
		daoService.save(professional);
		// Check that the professional entity is correctly saved in the database
		final List<Professional> professionalList1 = daoService.getAll(Professional.class);
		Assert.assertEquals(1, professionalList1.size());
		// Get the entity from database and check some properties
		final Professional professional1 = professionalList1.get(0);
		Assert.assertEquals("KONE", professional1.getFirstName());
		Assert.assertEquals("Seydou", professional1.getLastName());
		// Add phones for the created professional
		professional1.getPhones().put("MOBILE", "10.5.2.23.50");
		professional1.getPhones().put("LAND", "20.15.20.26.60");
		// Update the professional
		daoService.save(professional1);
		// Check that the phone numbers have been correctly saved
		final List<Professional> professionalList2 = daoService.getAll(Professional.class);
		Assert.assertEquals(1, professionalList2.size());
		final Professional professional2 = professionalList2.get(0);
		Assert.assertEquals(2, professional2.getPhones().size());
		// Update the professional with working days
		professional2.getWorkingDays().addAll(createWorkingDays());
		daoService.save(professional2);
		// Check that the working day have been correctly saved
		final List<Professional> professionalList3 = daoService.getAll(Professional.class);
		Assert.assertEquals(1, professionalList3.size());
		final Professional professional3 = professionalList3.get(0);
		Assert.assertEquals(Weekday.values().length - 1, professional3.getWorkingDays().size());
		final Optional<WorkingDay> monday = professional3.getWorkingDays().stream().filter(workingDay -> workingDay.getWeekday() == Weekday.MONDAY).findAny();
		Assert.assertTrue(monday.isPresent());
		Assert.assertEquals(2, monday.get().getTimeSlots().size());
		final TimeSlot firstTimeSlot = monday.get().getTimeSlots().stream().sorted(Comparator.comparing(TimeSlot::getStartTime)).findFirst().get();
		Assert.assertEquals(LocalTime.of(8, 0), firstTimeSlot.getStartTime());
		Assert.assertEquals(LocalTime.of(8, 30), firstTimeSlot.getEndTime());
	}
	
	@Test
	public void AppointmentTest(){
		final Appointment appointment = new Appointment();
		appointment.setAddress("25 avenue Pierre Gilles 13100 Aix-en-Provence");
		appointment.setClient("Maïmouna Zoundi");
		appointment.setDate(LocalDateTime.now().plusDays(10));
		appointment.setDuration(30);
		appointment.setEmail("jte@live.fr");
		
		final Professional professional = new Professional("jtraore", "jtraorepwd", "Jacques", "TRAORE", "Conseillé stratégie", "Aide les pays à s'auto-suffir quand c'est possible");
		
		daoService.save(professional);
		
		appointment.setProfessional(professional);
	}

}
