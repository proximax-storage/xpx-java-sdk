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

import io.proximax.xpx.exceptions.DecodeNemMessageFailureException;
import io.proximax.xpx.model.NemMessageType;
import org.nem.core.messages.PlainMessage;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;


/**
 * The Class AbstractPlainMessagePrivacyStrategy.
 */
public abstract class AbstractPlainMessagePrivacyStrategy extends PrivacyStrategy {

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#getNemMessageType()
     */
    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.PLAIN;
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#encodeToMessage(byte[])
     */
    @Override
    public Message encodeToMessage(byte[] data) {
        return new PlainMessage(data);
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#decodeTransaction(org.proximax.core.model.TransferTransaction)
     */
    @Override
    public byte[] decodeTransaction(TransferTransaction transaction) {
        if (transaction.getMessage().getType() == MessageTypes.SECURE)
            throw new DecodeNemMessageFailureException("Unable to decode secure message with plain privacy strategy");
        return transaction.getMessage().getDecodedPayload();
    }
}
