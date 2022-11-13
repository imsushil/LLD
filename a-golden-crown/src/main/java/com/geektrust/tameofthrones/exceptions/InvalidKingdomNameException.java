package com.geektrust.tameofthrones.exceptions;

public class InvalidKingdomNameException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidKingdomNameException(String msg) {
		super(msg);
	}
}
