package com.bougouri.timetable.app.web.model;

import com.bougouri.timetable.business.model.Holiday;

public class HolidayModel extends AbstractModel<Holiday> {

	private String startDateTime;
	private String endDateTime;

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
