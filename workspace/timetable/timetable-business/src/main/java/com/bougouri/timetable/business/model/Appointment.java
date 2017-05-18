package com.bougouri.timetable.business.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "APPOINTMENT_DATE", nullable = false)
	private LocalDateTime date;
	
	/**
	 * Duration in minutes
	 */
	@Min(1)
	@Column(name = "DURATION", nullable = false)
	private int duration;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "PROFESSIONAL_ID", referencedColumnName = "ID", nullable = false)
	private Professional professional;
	
	@NotNull
	@Column(name = "CLIENT_NAME", nullable = false)
	private String client;
	
	@Column(name = "CLIENT_EMAIL")
	private String email;
	
	@Column(name = "CLIENT_ADDRESS")
	private String address;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUMBER")
	@CollectionTable(name = "APPOINTMENT_PHONES")
	private final Map<String, String> phones = new HashMap<>();
	
	public final LocalDateTime getDate() {
		return date;
	}
	
	public final void setDate(final LocalDateTime date) {
		this.date = date;
	}
	
	/**
	 * The duration is expressed in minutes
	 *
	 * @return
	 */
	public final int getDuration() {
		return duration;
	}
	
	/**
	 * The duration is expressed in minutes
	 *
	 * @param duration
	 *            Duration in minutes
	 */
	public final void setDuration(final int duration) {
		this.duration = duration;
	}
	
	public final Professional getProfessional() {
		return professional;
	}
	
	public final void setProfessional(final Professional professional) {
		this.professional = professional;
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
