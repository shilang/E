package com.cloud.erp.service.exception;

public class BusinessStatusLimitedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BusinessStatusLimitedException() {
		super();
	}

	public BusinessStatusLimitedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessStatusLimitedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessStatusLimitedException(String message) {
		super(message);
	}

	public BusinessStatusLimitedException(Throwable cause) {
		super(cause);
	}
	
}
