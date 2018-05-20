package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Test;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;

import java.util.Arrays;

import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class SecuredWithNemKeysPrivacyStrategyTest {

    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    public static final CryptoEngine engine = CryptoEngines.ed25519Engine();
    public static final KeyPair SENDER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY), engine);
    public static final KeyPair RECEIVER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2), engine);
    public static final KeyPair ANOTHER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_3), engine);

    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPrivateKey() {
        new SecuredWithNemKeysPrivacyStrategy(null, RECEIVER_KEYPAIR.getPublicKey().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPublicKey() {
        new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), null);
    }

    @Test
    public void returnEncryptedWithKeys() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsSender() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        final byte[] decrypted = unitUnderTest.decrypt(sampleEncryptedData(), aSampleTransaction(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(RECEIVER_KEYPAIR.getPrivateKey().toString(), SENDER_KEYPAIR.getPublicKey().toString());

        final byte[] decrypted = unitUnderTest.decrypt(sampleEncryptedData(), aSampleTransaction(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test(expected = RuntimeException.class)
    public void failWhenPrivateKeyIsNeitherSenderOrReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(ANOTHER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        unitUnderTest.decrypt(SAMPLE_DATA, aSampleTransaction(), aSampleResourceHashMessage());
    }

    private byte[] sampleEncryptedData() {
        return engine.createBlockCipher(
                new KeyPair(SENDER_KEYPAIR.getPrivateKey(), engine),
                new KeyPair(RECEIVER_KEYPAIR.getPublicKey(), engine)).encrypt(SAMPLE_DATA);
    }

    private TransferTransaction aSampleTransaction() {
        return new TransferTransaction(null, new Account(SENDER_KEYPAIR), new Account(RECEIVER_KEYPAIR), null, null);
   }

    private ResourceHashMessage aSampleResourceHashMessage() {
        return new ResourceHashMessage();
    }
}
