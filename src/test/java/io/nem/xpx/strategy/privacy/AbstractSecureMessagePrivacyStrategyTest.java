package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecodeNemMessageFailureException;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.*;

import static io.nem.xpx.testsupport.Constants.*;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AbstractSecureMessagePrivacyStrategyTest {

    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    public static final KeyPair KEY_PAIR_1 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));
    public static final KeyPair KEY_PAIR_2 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2));
    public static final KeyPair KEY_PAIR_3 = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_3));

    public AbstractSecureMessagePrivacyStrategy unitUnderTest;

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

    @Test
    public void shouldReturnMessageTypeSecure() {
        assertEquals(NemMessageType.SECURE, unitUnderTest.getNemMessageType());
    }

    @Test
    public void shouldReturnPlainMessageWhenEncoding() {

        final Message message = unitUnderTest.encodeToMessage(INPUT_DATA);

        assertNotNull(message);
        assertEquals(MessageTypes.SECURE, message.getType());
        assertNotEquals(INPUT_DATA, message.getEncodedPayload());
    }

    @Test(expected = DecodeNemMessageFailureException.class)
    public void failDecodeWhenPrivateKeyIsNeitherSenderOrReceiver() {
        unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_3, KEY_PAIR_2, unitUnderTest.encodeToMessage(INPUT_DATA)));
    }

    @Test
    public void shouldReturnDecodeMessageWhenPrivateKeyIsSender() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_1, KEY_PAIR_2, unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertArrayEquals(decodedPayload, INPUT_DATA);
    }

    @Test
    public void shouldReturnDecodeMessageWhenPrivateKeyIsReceiver() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(
                encodedMessageTransaction(KEY_PAIR_2, KEY_PAIR_1, unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertArrayEquals(decodedPayload, INPUT_DATA);
    }

    private TransferTransaction encodedMessageTransaction(KeyPair sender, KeyPair receiver, Message message) {
        return new TransferTransaction(null, new Account(sender), new Account(receiver), null,
                new TransferTransactionAttachment(message));
    }
}
