package com.cloud.erp.dao.exception;

public class NumberIncrementException extends Exception {

	private static final long serialVersionUID = 1L;

	public NumberIncrementException() {
		super();
	}

	public NumberIncrementException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumberIncrementException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberIncrementException(String message) {
		super(message);
	}

	public NumberIncrementException(Throwable cause) {
		super(cause);
	}

}
