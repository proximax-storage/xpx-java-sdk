package io.nem.xpx.exceptions;


/**
 * The Class DecryptionFailureException.
 */
public class DecryptionFailureException extends RuntimeException {

	/**
	 * Instantiates a new decryption failure exception.
	 *
	 * @param message the message
	 */
	public DecryptionFailureException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new decryption failure exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DecryptionFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
