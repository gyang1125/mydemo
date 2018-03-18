package com.bmw.test.exception;

public class PositionNotFoundException extends RuntimeException {
	
	public PositionNotFoundException() {
		super();
	}

	public PositionNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public PositionNotFoundException(final String message) {
		super(message);
	}

	public PositionNotFoundException(final Throwable cause) {
		super(cause);
	}

}
