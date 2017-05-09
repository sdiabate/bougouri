package com.bougouri.timetable.app.web.model;

import com.bougouri.timetable.business.model.Appointment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentModel extends AbstractModel<Appointment> {
	
	@JsonProperty
	private String date;
	
	@JsonProperty
	private int duration;
	
	@JsonProperty
	private String professionalId;
	
	@JsonProperty
	private String client;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String address;
	
	public final String getDate() {
		return date;
	}
	
	public final void setDate(final String date) {
		this.date = date;
	}
	
	public final int getDuration() {
		return duration;
	}
	
	public final void setDuration(final int duration) {
		this.duration = duration;
	}
	
	public final String getProfessionalId() {
		return professionalId;
	}
	
	public final void setProfessionalId(final String professionalId) {
		this.professionalId = professionalId;
	}
	
	public final String getClient() {
		return client;
	}
	
	public final void setClient(final String client) {
		this.client = client;
	}
	
	public final String getEmail() {
		return email;
	}
	
	public final void setEmail(final String email) {
		this.email = email;
	}
	
	public final String getAddress() {
		return address;
	}
	
	public final void setAddress(final String address) {
		this.address = address;
	}
	
	@Override
	public Appointment to(final Appointment entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AppointmentModel from(final Appointment entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
