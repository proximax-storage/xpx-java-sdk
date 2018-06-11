package io.nem.xpx.facade.download;





/**
 * The Class DownloadException.
 */
public class DownloadException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new download exception.
	 *
	 * @param exception the exception
	 */
	public DownloadException(Exception exception) {

		super(exception);
	}

	/**
	 * Instantiates a new download exception.
	 *
	 * @param message the message
	 * @param exception the exception
	 */
	public DownloadException(String message, Exception exception) {

		super(message, exception);
	}

	/**
	 * Instantiates a new download exception.
	 *
	 * @param message the message
	 */
	public DownloadException(String message) {

		super(message);
	}

}
