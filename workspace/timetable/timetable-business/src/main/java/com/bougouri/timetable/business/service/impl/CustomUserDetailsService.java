package com.bougouri.timetable.business.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bougouri.timetable.business.dao.repository.IUserRepository;
import com.bougouri.timetable.business.model.security.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<User> user = userRepository.findFirstByLogin(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("User with login %s doen't exist", username));
		}
		return new CustomUserDetails(user.get());
	}

}
