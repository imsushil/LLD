package com.geektrust.tameofthrones.exceptions;

public class InvalidMessageFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidMessageFormatException(String msg) {
		super(msg);
	}
}
