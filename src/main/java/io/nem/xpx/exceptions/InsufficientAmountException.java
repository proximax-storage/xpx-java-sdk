package io.nem.xpx.exceptions;


import io.nem.xpx.facade.upload.UploadException;

/**
 * The Class InsufficientAmountException.
 */
public class InsufficientAmountException extends UploadException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new insufficient amount exception.
	 *
	 * @param exception the exception
	 */
	public InsufficientAmountException(Exception exception) {
		
		super(exception);
	}
	
	/**
	 * Instantiates a new insufficient amount exception.
	 *
	 * @param message the message
	 */
	public InsufficientAmountException(String message) {
		
		super(message);
	}
	
}
