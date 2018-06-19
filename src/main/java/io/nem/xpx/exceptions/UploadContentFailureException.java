package io.nem.xpx.exceptions;

public class UploadContentFailureException extends RuntimeException {


    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     */
    public UploadContentFailureException(String message) {
        super(message);
    }

    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public UploadContentFailureException(String message, Throwable cause) {
        super(message, cause);
    }


}
