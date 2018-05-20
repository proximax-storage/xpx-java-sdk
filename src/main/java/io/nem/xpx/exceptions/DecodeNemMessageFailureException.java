package io.nem.xpx.exceptions;

public class DecodeNemMessageFailureException extends RuntimeException {

	public DecodeNemMessageFailureException(String message) {
		super(message);
	}

	public DecodeNemMessageFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
