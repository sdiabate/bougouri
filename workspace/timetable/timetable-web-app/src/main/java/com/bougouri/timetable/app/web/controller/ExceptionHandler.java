package com.bougouri.timetable.app.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.bougouri.timetable.business.service.Exception.BusinessException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		if (ex instanceof BusinessException) {
			request.setAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE, ex.getMessage(), RequestAttributes.SCOPE_REQUEST);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
