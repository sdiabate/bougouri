package com.bougouri.timetable.app.web.model;

import com.bougouri.timetable.business.model.Holiday;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HolidayModel extends AbstractModel<Holiday> {

	@JsonProperty
	private long professionalId;
	
	@JsonProperty
	private String startDateTime;
	
	@JsonProperty
	private String endDateTime;
	
	public final long getProfessionalId() {
		return professionalId;
	}
	
	public final void setProfessionalId(final long professionalId) {
		this.professionalId = professionalId;
	}

	public final String getStartDateTime() {
		return startDateTime;
	}

	public final void setStartDateTime(final String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public final String getEndDateTime() {
		return endDateTime;
	}

	public final void setEndDateTime(final String endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public Holiday to(final Holiday entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HolidayModel from(final Holiday entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
