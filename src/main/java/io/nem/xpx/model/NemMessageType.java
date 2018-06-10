package io.nem.xpx.model;

import org.nem.core.model.MessageTypes;

import java.util.stream.Stream;

public enum NemMessageType {

    PLAIN(MessageTypes.PLAIN),
    SECURE(MessageTypes.SECURE);

    private int value;

    NemMessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NemMessageType fromInt(int type) {
        return Stream.of(values()).filter(messageType -> messageType.getValue() == type).findFirst().orElse(null);
    }

}
