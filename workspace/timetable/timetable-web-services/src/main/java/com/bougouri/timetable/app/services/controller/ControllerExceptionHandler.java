package com.bougouri.timetable.app.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bougouri.timetable.business.service.Exception.BusinessException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<Result> handleBusinessException(final BusinessException exception) {
		LOGGER.error("", exception);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Result(exception.getType().name(), exception.getMessage()));
	}
}
