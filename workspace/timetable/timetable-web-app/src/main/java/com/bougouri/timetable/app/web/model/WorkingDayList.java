package com.bougouri.timetable.app.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkingDayList {
	
	@JsonProperty
	private long professionalId;
	
	@JsonProperty
	private List<WorkingDayModel> workingDayModels;

	public final long getProfessionalId() {
		return professionalId;
	}

	public final void setProfessionalId(final long professionalId) {
		this.professionalId = professionalId;
	}

	public final List<WorkingDayModel> getWorkingDayModels() {
		return workingDayModels;
	}

	public final void setWorkingDayModels(final List<WorkingDayModel> workingDayModels) {
		this.workingDayModels = workingDayModels;
	}

}
