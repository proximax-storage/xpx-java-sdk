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

package io.proximax.xpx.strategy.privacy;

import io.proximax.xpx.model.NemMessageType;
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;


/**
 * The Class PrivacyStrategy.
 */
public abstract class PrivacyStrategy {

    /**
     * Gets the proximax message type.
     *
     * @return the proximax message type
     */
    public abstract NemMessageType getNemMessageType();

    /**
     * Encrypt.
     *
     * @param data the data
     * @return the byte[]
     */
    public abstract byte[] encrypt(final byte[] data);
    
    /**
     * Decrypt.
     *
     * @param data the data
     * @param transaction the transaction
     * @param hashMessage the hash message
     * @return the byte[]
     */
    public abstract byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage);

    /**
     * Encode to message.
     *
     * @param payload the payload
     * @return the message
     */
    public abstract Message encodeToMessage(final byte[] payload);
    
    /**
     * Decode transaction.
     *
     * @param transaction the transaction
     * @return the byte[]
     */
    public abstract byte[] decodeTransaction(final TransferTransaction transaction);

}
