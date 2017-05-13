package com.bougouri.timetable.business.model.security;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bougouri.timetable.business.model.AbstractEntity;

@Entity
@Table(name = "USERS"/* , uniqueConstraints = @UniqueConstraint(columnNames = { "LOGIN" }) */)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
@DiscriminatorValue("USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 3, max = 32)
	@Column(name = "LOGIN", unique = true, nullable = false)
	private String login;

	@NotNull
	@Size(min = 8)
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotNull
	@Size(min = 1)
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotNull
	@Size(min = 1)
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PROFILE", nullable = false)
	private Profile profile;

	/**
	 * @param login
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param description
	 * @param profile
	 */
	public User(final String login, final String password, final String firstName, final String lastName, final String description, final Profile profile) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.profile = profile;
	}

	public User() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(final Profile profile) {
		this.profile = profile;
	}

	public boolean isAdmin() {
		return profile == Profile.ADMIN;
	}
}
