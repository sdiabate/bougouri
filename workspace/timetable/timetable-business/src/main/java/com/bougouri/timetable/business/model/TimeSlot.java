package com.bougouri.timetable.business.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "TIME_SLOT")
public class TimeSlot extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalTime")
	@Column(name = "START_TIME", nullable = false)
	private LocalTime startTime;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalTime")
	@Column(name = "END_TIME", nullable = false)
	private LocalTime endTime;

	public TimeSlot() {
	}

	public TimeSlot(final LocalTime startTime, final LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public final LocalTime getStartTime() {
		return startTime;
	}

	public final void setStartTime(final LocalTime startTime) {
		this.startTime = startTime;
	}

	public final LocalTime getEndTime() {
		return endTime;
	}

	public final void setEndTime(final LocalTime endTime) {
		this.endTime = endTime;
	}

}
