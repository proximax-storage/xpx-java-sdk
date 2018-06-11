package io.nem.xpx.facade.upload;





/**
 * The Class UploadException.
 */
public class UploadException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new upload exception.
	 *
	 * @param exception the exception
	 */
	public UploadException(Exception exception) {
		
		super(exception);
	}

	/**
	 * Instantiates a new upload exception.
	 *
	 * @param message the message
	 * @param exception the exception
	 */
	public UploadException(String message, Exception exception) {

		super(message, exception);
	}

	/**
	 * Instantiates a new upload exception.
	 *
	 * @param message the message
	 */
	public UploadException(String message) {

		super(message);
	}

}
