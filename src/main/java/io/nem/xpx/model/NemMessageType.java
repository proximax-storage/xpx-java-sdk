package io.nem.xpx.model;

import org.nem.core.model.MessageTypes;

import java.util.stream.Stream;


/**
 * The Enum NemMessageType.
 */
public enum NemMessageType {

    /** The plain. */
    PLAIN(MessageTypes.PLAIN),
    
    /** The secure. */
    SECURE(MessageTypes.SECURE);

    /** The value. */
    private int value;

    /**
     * Instantiates a new nem message type.
     *
     * @param value the value
     */
    NemMessageType(int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * From int.
     *
     * @param type the type
     * @return the nem message type
     */
    public static NemMessageType fromInt(int type) {
        return Stream.of(values()).filter(messageType -> messageType.getValue() == type).findFirst().orElse(null);
    }

}
