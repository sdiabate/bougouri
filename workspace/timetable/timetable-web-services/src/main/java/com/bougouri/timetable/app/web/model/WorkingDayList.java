package com.bougouri.timetable.app.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkingDayList {
	
	private final long professionalId;
	
	private final List<WorkingDayModel> workingDayModels;

	@JsonCreator
	public WorkingDayList(@JsonProperty final long professionalId, @JsonProperty final List<WorkingDayModel> workingDayModels) {
		this.professionalId = professionalId;
		this.workingDayModels = workingDayModels;
	}

	public final long getProfessionalId() {
		return professionalId;
	}

	public final List<WorkingDayModel> getWorkingDayModels() {
		return workingDayModels;
	}

}
