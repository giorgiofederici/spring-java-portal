package com.giorgiofederici.sjp.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7667596895819034026L;

	private String username;

	public UserNotFoundException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
}
