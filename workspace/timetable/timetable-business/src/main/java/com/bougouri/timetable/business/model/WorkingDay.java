package com.bougouri.timetable.business.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKING_DAY")
public class WorkingDay extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "WEEKDAY")
	private Weekday weekday;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "WORKING_DAY_ID", referencedColumnName = "ID")
	private final Set<TimeSlot> timeSlots = new HashSet<>();

	public WorkingDay() {
	}

	public WorkingDay(final Weekday weekday) {
		this.weekday = weekday;
	}

	public final Weekday getWeekday() {
		return weekday;
	}

	public final void setWeekday(final Weekday weekday) {
		this.weekday = weekday;
	}

	public final Set<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

}
