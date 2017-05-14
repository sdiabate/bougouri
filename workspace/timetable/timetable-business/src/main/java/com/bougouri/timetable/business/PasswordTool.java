package com.bougouri.timetable.business;

import org.springframework.security.crypto.codec.Base64;

public class PasswordTool {
	
	public static void main(final String[] args) {
		System.out.println(new String(Base64.encode("admin".getBytes())));
	}
	
}
