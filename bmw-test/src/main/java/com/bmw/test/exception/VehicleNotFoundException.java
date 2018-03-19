package com.bmw.test.exception;

/**
 * Class <code>VehicleNotFoundException</code> is exception to deal with as
 * <code>vin</code> is not found
 * 
 * @author gyang
 *
 */
public final class VehicleNotFoundException extends RuntimeException {

	public VehicleNotFoundException() {
		super();
	}

	public VehicleNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public VehicleNotFoundException(final String message) {
		super(message);
	}

	public VehicleNotFoundException(final Throwable cause) {
		super(cause);
	}

}