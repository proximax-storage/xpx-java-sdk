/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.model;

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
     * Instantiates a new proximax message type.
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
     * @return the proximax message type
     */
    public static NemMessageType fromInt(int type) {
        return Stream.of(values()).filter(messageType -> messageType.getValue() == type).findFirst().orElse(null);
    }

}
