package com.bougouri.timetable.business.service.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bougouri.timetable.business.model.security.User;

@Component
public class UserAuditorAware implements AuditorAware<User> {
	
	@Override
	public User getCurrentAuditor() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.isAuthenticated() ? (User) authentication.getPrincipal() : null;
	}
}