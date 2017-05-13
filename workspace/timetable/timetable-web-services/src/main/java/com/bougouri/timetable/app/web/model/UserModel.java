package com.bougouri.timetable.app.web.model;

import java.util.stream.Stream;

import com.bougouri.timetable.business.model.security.Profile;
import com.bougouri.timetable.business.model.security.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel<T extends User> extends AbstractModel<T> {

	@JsonProperty
	private String login;

	@JsonProperty
	private String password;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;

	@JsonProperty
	private String profile;

	@JsonProperty
	private String description;

	public UserModel() {
	}

	public UserModel(final String login, final String password, final String firstName, final String lastName, final String profile, final String description) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile = profile;
		this.description = description;
	}

	@Override
	public T to(final T entity) {
		entity.setLogin(login);
		entity.setPassword(password);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		if (profile != null && Stream.of(Profile.values()).anyMatch(p -> p.name().equalsIgnoreCase(profile))) {
			entity.setProfile(Profile.valueOf(profile.toUpperCase()));
		}
		entity.setDescription(description);
		return entity;
	}

	@Override
	public UserModel<T> from(final T entity) {
		login = entity.getLogin();
		password = entity.getPassword();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		profile = entity.getProfile().name();
		description = entity.getDescription();
		return this;
	}

}
