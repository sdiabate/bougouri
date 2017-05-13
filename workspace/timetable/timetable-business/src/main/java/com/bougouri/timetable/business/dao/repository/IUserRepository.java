package com.bougouri.timetable.business.dao.repository;

import java.util.Optional;

import com.bougouri.timetable.business.model.security.User;

public interface IUserRepository extends IBasicRepository<User, Long> {
	Optional<User> findFirstByLogin(String login);
}
