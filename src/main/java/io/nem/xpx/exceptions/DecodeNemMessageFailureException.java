package io.nem.xpx.exceptions;


/**
 * The Class DecodeNemMessageFailureException.
 */
public class DecodeNemMessageFailureException extends RuntimeException {

	/**
	 * Instantiates a new decode nem message failure exception.
	 *
	 * @param message the message
	 */
	public DecodeNemMessageFailureException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new decode nem message failure exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DecodeNemMessageFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
