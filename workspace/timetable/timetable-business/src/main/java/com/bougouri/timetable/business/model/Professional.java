package com.bougouri.timetable.business.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.bougouri.timetable.business.model.security.User;

@Entity
@Table(name = "PROFESSIONAL")
@DiscriminatorValue("PROFESSIONAL")
public class Professional extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "SPECIALITY")
	private String speciality;

	@Column(name = "ADDRESS")
	private String address;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUMBER")
	@CollectionTable(name = "PROFESSIONAL_PHONES")
	private final Map<String, String> phones = new HashMap<>();

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

}
