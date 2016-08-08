package com.cloud.erp.exceptions;

public class UpdateReferenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateReferenceException() {
		super();
	}

	public UpdateReferenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateReferenceException(String message) {
		super(message);
	}

	public UpdateReferenceException(Throwable cause) {
		super(cause);
	}

}
