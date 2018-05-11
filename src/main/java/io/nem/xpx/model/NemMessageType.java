package io.nem.xpx.model;

import org.nem.core.model.MessageTypes;

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
}
