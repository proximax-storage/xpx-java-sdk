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
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.*;

import static io.proximax.xpx.testsupport.Constants.*;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * The Class AbstractSecureMessagePrivacyStrategyTest.
 */
public class AbstractSecureMessagePrivacyStrategyTest {

    /** The Constant INPUT_DATA. */
    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    
    /** The Constant KEY_PAIR_1. */
    public static final KeyPair KEY_PAIR_1 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));
    
    /** The Constant KEY_PAIR_2. */
    public static final KeyPair KEY_PAIR_2 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2));
    
    /** The Constant KEY_PAIR_3. */
    public static final KeyPair KEY_PAIR_3 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_3));

    /** The unit under test. */
    public AbstractSecureMessagePrivacyStrategy unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new AbstractSecureMessagePrivacyStrategy(KEY_PAIR_1.getPrivateKey().toString(), KEY_PAIR_2.getPublicKey().toString()) {
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
     * Should return message type secure.
     */
    @Test
    public void shouldReturnMessageTypeSecure() {
        assertEquals(NemMessageType.SECURE, unitUnderTest.getNemMessageType());
    }

    /**
     * Should return plain message when encoding.
     */
    @Test
    public void shouldReturnPlainMessageWhenEncoding() {

        final Message message = unitUnderTest.encodeToMessage(INPUT_DATA);

        assertNotNull(message);
        assertEquals(MessageTypes.SECURE, message.getType());
        assertNotEquals(INPUT_DATA, message.getEncodedPayload());
    }

    /**
     * Fail decode when private key is neither sender or receiver.
     */
    @Test(expected = DecodeNemMessageFailureException.class)
    public void failDecodeWhenPrivateKeyIsNeitherSenderOrReceiver() {
        unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_3, KEY_PAIR_2, unitUnderTest.encodeToMessage(INPUT_DATA)));
    }

    /**
     * Should return decode message when private key is sender.
     */
    @Test
    public void shouldReturnDecodeMessageWhenPrivateKeyIsSender() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_1, KEY_PAIR_2, unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertArrayEquals(decodedPayload, INPUT_DATA);
    }

    /**
     * Should return decode message when private key is receiver.
     */
    @Test
    public void shouldReturnDecodeMessageWhenPrivateKeyIsReceiver() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_2, KEY_PAIR_1, unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertArrayEquals(decodedPayload, INPUT_DATA);
    }

    /**
     * Encoded message transaction.
     *
     * @param sender the sender
     * @param receiver the receiver
     * @param message the message
     * @return the transfer transaction
     */
    private TransferTransaction encodedMessageTransaction(KeyPair sender, KeyPair receiver, Message message) {
        return new TransferTransaction(null, new Account(sender), new Account(receiver), null,
                new TransferTransactionAttachment(message));
    }
}
