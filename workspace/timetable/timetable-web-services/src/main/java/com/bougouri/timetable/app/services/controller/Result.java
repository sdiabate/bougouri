package com.bougouri.timetable.app.services.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	
	@JsonProperty
	private final Object data;
	
	@JsonProperty
	private final String exceptionCode;
	
	@JsonProperty
	private final String exceptionMessage;

	Result() {
		this(null);
	}

	public Result(final Object data) {
		this(data, null, null);
	}

	public Result(final String exceptionCode, final String exceptionMessage) {
		this(null, exceptionCode, exceptionMessage);
	}

	public Result(final Object data, final String exceptionCode, final String exceptionMessage) {
		this.data = data;
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getExceptionCode() {
		return exceptionCode;
	}
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
