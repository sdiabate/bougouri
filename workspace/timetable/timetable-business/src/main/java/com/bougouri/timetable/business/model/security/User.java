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
import javax.persistence.UniqueConstraint;

import com.bougouri.timetable.business.model.AbstractEntity;

@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "LOGIN_NAME" }))
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ACTOR_TYPE")
@DiscriminatorValue("USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "PROFILE")
	private Profile profile;

	/**
	 * @param loginName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param description
	 * @param profile
	 */
	public User(final String loginName, final String password, final String firstName, final String lastName, final String description, final Profile profile) {
		this.loginName = loginName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.profile = profile;
	}

	public User() {
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(final String loginName) {
		this.loginName = loginName;
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