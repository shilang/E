package com.cloud.erp.exceptions;

public class FoundMoreGroupOrRoleException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FoundMoreGroupOrRoleException() {
		
	}

	public FoundMoreGroupOrRoleException(String message) {
		super(message);
	}

	public FoundMoreGroupOrRoleException(Throwable cause) {
		super(cause);
	}

	public FoundMoreGroupOrRoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public FoundMoreGroupOrRoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
