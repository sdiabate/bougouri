package com.bougouri.timetable.business.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "HOLIDAY")
public class Holiday extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "START_DATE_TIME", nullable = false)
	private LocalDateTime startDateTime;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "END_DATE_TIME", nullable = false)
	private LocalDateTime endDateTime;

	public Holiday() {
	}
	
	public Holiday(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
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
