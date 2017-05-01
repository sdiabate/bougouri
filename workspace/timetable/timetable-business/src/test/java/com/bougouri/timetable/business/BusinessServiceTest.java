package com.bougouri.timetable.business;

import java.time.LocalTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.service.IBusinessService;
import com.bougouri.timetable.business.service.Exception.BusinessException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class BusinessServiceTest extends AbstractTest {

	@Autowired
	private IBusinessService businessService;

	@Test
	public void professionalRegistrationTest() {
		// Assert that there is no professional entity in the database
		Assertions.assertThat(daoService.getAll(Professional.class).size()).isEqualTo(0);

		// Create a new professional with basic properties settled
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "KONE", "Seydou", "");
		professional.setSpeciality("Gynecologue");

		// Save the new entity into the database
		try {
			businessService.registerProfessional(professional);
		} catch (final BusinessException e) {
			Assert.fail(e.getMessage());
		}

		// Check that the professional entity is correctly saved in the database
		final List<Professional> professionalList = daoService.getAll(Professional.class);
		Assertions.assertThat(professionalList.size()).isEqualTo(1);

		final Professional professionalDb = professionalList.get(0);
		professionalDb.getWorkingDays().addAll(createWorkingDays());
		professionalDb.getWorkingDays().get(0).getTimeSlots().add(new TimeSlot(LocalTime.of(8, 50), LocalTime.of(9, 0)));

		Assertions.assertThatThrownBy(() -> businessService.registerProfessional(professionalDb)).isInstanceOf(BusinessException.class).hasMessage("At least 2 time slot overlap");
	}
}
