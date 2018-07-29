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
import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.*;

import static io.proximax.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.proximax.xpx.testsupport.Constants.TEST_PRIVATE_KEY_2;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * The Class AbstractPlainMessagePrivacyStrategyTest.
 */
public class AbstractPlainMessagePrivacyStrategyTest {

    /** The Constant INPUT_DATA. */
    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    
    /** The Constant SENDER_KEYPAIR. */
    public static final KeyPair SENDER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));
    
    /** The Constant RECEIVER_KEYPAIR. */
    public static final KeyPair RECEIVER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2));

    /** The unit under test. */
    public AbstractPlainMessagePrivacyStrategy unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new AbstractPlainMessagePrivacyStrategy() {
            @Override
            public byte[] encrypt(byte[] data) {
                return null;
            }

            @Override
            public byte[] decrypt(byte[] data, TransferTransaction transaction, ResourceHashMessage hashMessage) {
                return null;
            }
        };
    }

    /**
     * Should return message type plain.
     */
    @Test
    public void shouldReturnMessageTypePlain() {
        assertEquals(NemMessageType.PLAIN, unitUnderTest.getNemMessageType());
    }

    /**
     * Should return plain message when encoding.
     */
    @Test
    public void shouldReturnPlainMessageWhenEncoding() {

        final Message message = unitUnderTest.encodeToMessage(INPUT_DATA);

        assertNotNull(message);
        assertEquals(MessageTypes.PLAIN, message.getType());
        assertEquals(INPUT_DATA, message.getEncodedPayload());

    }

    /**
     * Should return decode message.
     */
    @Test
    public void shouldReturnDecodeMessage() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(encodedMessageTransaction(unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertEquals(decodedPayload, INPUT_DATA);
    }

    /**
     * Encoded message transaction.
     *
     * @param message the message
     * @return the transfer transaction
     */
    private TransferTransaction encodedMessageTransaction(Message message) {
        return new TransferTransaction(null, new Account(SENDER_KEYPAIR), new Account(RECEIVER_KEYPAIR), null,
                new TransferTransactionAttachment(message));
   }
}
