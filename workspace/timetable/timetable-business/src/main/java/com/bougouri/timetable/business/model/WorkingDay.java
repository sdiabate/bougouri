package com.bougouri.timetable.business.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKING_DAY")
public class WorkingDay {

	@Enumerated(EnumType.STRING)
	@Column(name = "WEEKDAY")
	private Weekday weekday;

	@OneToMany
	@JoinColumn(name = "WORKING_DAY_TIME_SLOT_ID", referencedColumnName = "ID")
	private Set<TimeSlot> timeSlots;

	public final Weekday getWeekday() {
		return weekday;
	}

	public final void setWeekday(final Weekday weekday) {
		this.weekday = weekday;
	}

	public final Set<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public final void setTimeSlots(final Set<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

}
