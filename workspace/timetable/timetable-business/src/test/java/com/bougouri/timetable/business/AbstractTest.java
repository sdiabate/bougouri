package com.bougouri.timetable.business;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.model.Weekday;
import com.bougouri.timetable.business.model.WorkingDay;
import com.bougouri.timetable.business.service.impl.BasicDaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
@ActiveProfiles("dev")
public class AbstractTest {
	
	@Autowired
	protected BasicDaoService daoService;
	
	@After
	public void cleanDatabase() {
		daoService.deleteAll(Professional.class);
		daoService.deleteAll(Appointment.class);
	}
	
	protected List<WorkingDay> createWorkingDays() {
		return Stream.of(Weekday.values()).filter(weekday -> weekday != Weekday.SUNDAY).map(weekday -> createWorkingDay(weekday)).collect(Collectors.toList());
	}
	
	private WorkingDay createWorkingDay(final Weekday weekday) {
		final WorkingDay workingDay = new WorkingDay(weekday);
		workingDay.getTimeSlots().add(new TimeSlot(LocalTime.of(8, 0), LocalTime.of(8, 30)));
		workingDay.getTimeSlots().add(new TimeSlot(LocalTime.of(8, 30), LocalTime.of(9, 0)));
		return workingDay;
	}
	
}
