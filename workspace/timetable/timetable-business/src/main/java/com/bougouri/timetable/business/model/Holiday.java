package com.bougouri.timetable.business.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "HOLIDAY")
public class Holiday extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "START_DATE_TIME")
	private LocalDateTime startDateTime;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "END_DATE_TIME")
	private LocalDateTime endDateTime;

	public final LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public final void setStartDateTime(final LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public final LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public final void setEndDateTime(final LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

}
