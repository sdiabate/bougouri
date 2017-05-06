package com.bougouri.timetable.app.web.model;

import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.security.Profile;

public class ProfessionalModel extends UserModel<Professional> {

	private String speciality;
	private String address;

	public ProfessionalModel() {
	}

	public ProfessionalModel(final String login, final String password, final String firstName, final String lastName, final String description) {
		super(login, password, firstName, lastName, Profile.PROFESSIONAL.name(), description);
	}

	public void setSpeciality(final String speciality) {
		this.speciality = speciality;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Override
	public void to(final Professional entity) {
		super.to(entity);
		entity.setSpeciality(speciality);
		entity.setAddress(address);
	}

	@Override
	public ProfessionalModel from(final Professional entity) {
		super.from(entity);
		speciality = entity.getSpeciality();
		address = entity.getAddress();
		return this;
	}

}
