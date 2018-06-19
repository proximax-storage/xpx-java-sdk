package io.nem.xpx.exceptions;

public class DeletePinnedContentFailureException extends RuntimeException {


    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     */
    public DeletePinnedContentFailureException(String message) {
        super(message);
    }

    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public DeletePinnedContentFailureException(String message, Throwable cause) {
        super(message, cause);
    }


}
