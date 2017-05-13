package com.bougouri.timetable.business.service.Exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ExceptionType type;

	public BusinessException(final ExceptionType type, final String message, final Throwable cause) {
		super(message, cause);
		this.type = type;
	}

	public BusinessException(final ExceptionType type, final String message) {
		super(message);
		this.type = type;
	}

	public BusinessException(final ExceptionType type, final Throwable cause) {
		super(cause);
		this.type = type;
	}

	public final ExceptionType getType() {
		return type;
	}

}
