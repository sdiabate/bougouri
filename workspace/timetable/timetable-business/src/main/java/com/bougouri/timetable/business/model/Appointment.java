package com.bougouri.timetable.business.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.Type;

public class Appointment {

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@Column(name = "APPOINTMENT_DATE")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "TIME_SLOT_ID", referencedColumnName = "ID")
	private TimeSlot timeSlot;

	@Column(name = "CLIENT_NAME")
	private String client;

	@Column(name = "CLIENT_EMAIL")
	private String email;

	@Column(name = "CLIENT_ADDRESS")
	private String address;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUMBER")
	@CollectionTable(name = "PROFESSIONAL_PHONES")
	private final Map<String, String> phones = new HashMap<>();

	public final LocalDate getDate() {
		return date;
	}

	public final void setDate(final LocalDate date) {
		this.date = date;
	}

	public final TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public final void setTimeSlot(final TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
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

	public final Map<String, String> getPhones() {
		return phones;
	}

}
