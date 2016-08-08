package com.cloud.erp.exceptions;

public class FoundMoreUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FoundMoreUserException() {
	}

	public FoundMoreUserException(String message) {
		super(message);
	}

	public FoundMoreUserException(Throwable cause) {
		super(cause);
	}

	public FoundMoreUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public FoundMoreUserException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
