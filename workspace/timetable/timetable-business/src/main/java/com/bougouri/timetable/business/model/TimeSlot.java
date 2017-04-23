package com.bougouri.timetable.business.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TIME_SLOT")
public class TimeSlot extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	private LocalTime startTime;

	@Temporal(TemporalType.TIMESTAMP)
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
