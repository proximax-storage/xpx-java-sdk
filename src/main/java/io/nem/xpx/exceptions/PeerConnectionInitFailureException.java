package io.nem.xpx.exceptions;

public class PeerConnectionInitFailureException extends RuntimeException {


    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     */
    public PeerConnectionInitFailureException(String message) {
        super(message);
    }

    /**
     * Instantiates a new decode nem message failure exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public PeerConnectionInitFailureException(String message, Throwable cause) {
        super(message, cause);
    }


}
