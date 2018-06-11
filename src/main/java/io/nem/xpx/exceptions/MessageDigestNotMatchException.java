package io.nem.xpx.exceptions;





/**
 * The Class MessageDigestNotMatchException.
 */
public class MessageDigestNotMatchException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new resource not found exception.
	 */
	public MessageDigestNotMatchException() {
		super();
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public MessageDigestNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the message
	 */
	public MessageDigestNotMatchException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param cause the cause
	 */
	public MessageDigestNotMatchException(Throwable cause) {
		super(cause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return "MessageDigestNotMatchException []";
	}

}

