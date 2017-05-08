package com.bougouri.timetable.business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bougouri.timetable.business.model.security.Profile;
import com.bougouri.timetable.business.model.security.User;

@Entity
@Table(name = "PROFESSIONAL")
@DiscriminatorValue("PROFESSIONAL")
public class Professional extends User {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1)
	@Column(name = "SPECIALITY", nullable = false)
	private String speciality;

	@Column(name = "ADDRESS")
	private String address;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUMBER")
	@CollectionTable(name = "PROFESSIONAL_PHONES")
	private final Map<String, String> phones = new HashMap<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFESSIONAL_ID", referencedColumnName = "ID")
	private final List<WorkingDay> workingDays = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFESSIONAL_ID", referencedColumnName = "ID")
	private final List<Holiday> holidays = new ArrayList<>();

	public Professional() {
	}

	public Professional(final String loginName, final String password, final String firstName, final String lastName, String speciality, final String description) {
		super(loginName, password, firstName, lastName, description, Profile.PROFESSIONAL);
		setSpeciality(speciality);
	}

	public final String getSpeciality() {
		return speciality;
	}

	public final void setSpeciality(final String speciality) {
		this.speciality = speciality;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(final String address) {
		this.address = address;
	}

	public Map<String, String> getPhones() {
		return phones;
	}

	public final List<WorkingDay> getWorkingDays() {
		return workingDays;
	}

	public final List<Holiday> getHolidays() {
		return holidays;
	}

}
