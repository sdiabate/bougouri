package com.bougouri.timetable.business.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WORKING_DAY")
public class WorkingDay extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "WEEKDAY", nullable = false)
	private Weekday weekday;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "WORKING_DAY_ID", referencedColumnName = "ID")
	private final List<TimeSlot> timeSlots = new ArrayList<>();

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

	public final List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

}
