package com.giorgiofederici.sjp.exception;

public class AuthoritiesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1375786595897538204L;

	public AuthoritiesNotFoundException() {
		super();
	}

	public AuthoritiesNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AuthoritiesNotFoundException(final String message) {
		super(message);
	}

	public AuthoritiesNotFoundException(final Throwable cause) {
		super(cause);
	}

}
