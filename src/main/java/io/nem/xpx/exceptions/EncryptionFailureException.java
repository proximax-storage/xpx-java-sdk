package io.nem.xpx.exceptions;


/**
 * The Class EncryptionFailureException.
 */
public class EncryptionFailureException extends RuntimeException {

	/**
	 * Instantiates a new encryption failure exception.
	 *
	 * @param message the message
	 */
	public EncryptionFailureException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new encryption failure exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public EncryptionFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
