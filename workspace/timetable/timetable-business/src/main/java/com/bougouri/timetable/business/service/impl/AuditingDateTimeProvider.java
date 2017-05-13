package com.bougouri.timetable.business.service.impl;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.auditing.DateTimeProvider;

public class AuditingDateTimeProvider implements DateTimeProvider {

	@Override
	public Calendar getNow() {
		return GregorianCalendar.from(ZonedDateTime.now());
	}
}