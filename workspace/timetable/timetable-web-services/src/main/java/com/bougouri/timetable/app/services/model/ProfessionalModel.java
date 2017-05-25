package com.bougouri.timetable.app.services.model;

import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.security.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfessionalModel extends UserModel<Professional> {
	
	@JsonProperty
	private String specialty;
	
	@JsonProperty
	private String address;
	
	public ProfessionalModel() {
	}
	
	public ProfessionalModel(final String login, final String password, final String firstName, final String lastName, final String speciality, final String description) {
		super(login, password, firstName, lastName, Profile.PROFESSIONAL.name(), description);
		specialty = speciality;
	}
	
	public void setAddress(final String address) {
		this.address = address;
	}
	
	@Override
	public Professional to(final Professional entity) {
		super.to(entity);
		entity.setSpecialty(specialty);
		entity.setAddress(address);
		return entity;
	}
	
	@Override
	public ProfessionalModel from(final Professional entity) {
		super.from(entity);
		specialty = entity.getSpecialty();
		address = entity.getAddress();
		return this;
	}
	
}
