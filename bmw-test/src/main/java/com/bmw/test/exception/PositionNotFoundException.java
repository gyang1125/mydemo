package com.bmw.test.exception;

/**
 * Class <code>PositionNotFoundException</code> is exception to deal with as
 * <code>position</code> is not found
 * 
 * @author gyang
 *
 */
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
