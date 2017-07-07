package com.giorgiofederici.sjp.exception;

public final class SjpResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1843691638298939342L;

	public SjpResourceNotFoundException() {
		super();
	}

	public SjpResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SjpResourceNotFoundException(final String message) {
		super(message);
	}

	public SjpResourceNotFoundException(final Throwable cause) {
		super(cause);
	}
}
