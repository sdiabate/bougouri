package com.bougouri.timetable.business.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "TIME_SLOT")
public class TimeSlot extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalTime")
	@Column(name = "START_TIME")
	private LocalTime startTime;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalTime")
	@Column(name = "END_TIME")
	private LocalTime endTime;

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
