package com.bougouri.timetable.business.dao;

import org.springframework.security.crypto.codec.Base64;

public abstract class AbstractDataSourceConfig implements IDataSourceConfig {

	protected static String encode(final String token) {
		final byte[] encodedBytes = Base64.encode(token.getBytes());
		return new String(encodedBytes);
	}
	
	protected static String decode(final String token) {
		final byte[] decodedBytes = Base64.decode(token.getBytes());
		return new String(decodedBytes);
	}
}
