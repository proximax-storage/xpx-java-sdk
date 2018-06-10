package io.nem.xpx.strategy.privacy;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.*;

import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY_2;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AbstractPlainMessagePrivacyStrategyTest {

    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    public static final KeyPair SENDER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));
    public static final KeyPair RECEIVER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2));

    public AbstractPlainMessagePrivacyStrategy unitUnderTest;

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

    @Test
    public void shouldReturnMessageTypePlain() {
        assertEquals(NemMessageType.PLAIN, unitUnderTest.getNemMessageType());
    }

    @Test
    public void shouldReturnPlainMessageWhenEncoding() {

        final Message message = unitUnderTest.encodeToMessage(INPUT_DATA);

        assertNotNull(message);
        assertEquals(MessageTypes.PLAIN, message.getType());
        assertEquals(INPUT_DATA, message.getEncodedPayload());

    }

    @Test
    public void shouldReturnDecodeMessage() {

        final byte[] decodedPayload = unitUnderTest.decodeTransaction(encodedMessageTransaction(unitUnderTest.encodeToMessage(INPUT_DATA)));

        assertNotNull(decodedPayload);
        assertEquals(decodedPayload, INPUT_DATA);
    }

    private TransferTransaction encodedMessageTransaction(Message message) {
        return new TransferTransaction(null, new Account(SENDER_KEYPAIR), new Account(RECEIVER_KEYPAIR), null,
                new TransferTransactionAttachment(message));
   }
}
