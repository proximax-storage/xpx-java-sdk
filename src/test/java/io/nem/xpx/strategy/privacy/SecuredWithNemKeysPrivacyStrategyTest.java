package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Test;
import org.nem.core.crypto.*;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;

import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertArrayEquals;

public class SecuredWithNemKeysPrivacyStrategyTest {

    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    public static final CryptoEngine engine = CryptoEngines.ed25519Engine();
    public static final KeyPair KEYPAIR_FROM_PRIVATE_KEY = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY), engine);
    public static final KeyPair KEYPAIR_FROM_PUBLIC_KEY = new KeyPair(PublicKey.fromHexString(TEST_PUBLIC_KEY), engine);

    @Test
    public void returnEncryptedWithKeys() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest = new SecuredWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY);
        final byte[] expected = encrypt(SAMPLE_DATA, KEYPAIR_FROM_PRIVATE_KEY, KEYPAIR_FROM_PUBLIC_KEY);

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertArrayEquals(expected, encrypted);
    }

    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsSender() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest = new SecuredWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY);
        final byte[] encryptedData = encrypt(SAMPLE_DATA, KEYPAIR_FROM_PRIVATE_KEY, KEYPAIR_FROM_PUBLIC_KEY);

        final byte[] decrypted = unitUnderTest.decrypt(encryptedData, aSampleTransactionWithPrivateKeyAsSender(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest = new SecuredWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY);
        final byte[] encryptedData = encrypt(SAMPLE_DATA, KEYPAIR_FROM_PRIVATE_KEY, KEYPAIR_FROM_PUBLIC_KEY);

        final byte[] decrypted = unitUnderTest.decrypt(encryptedData, aSampleTransactionWithPrivateKeyAsReceiver(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test(expected = RuntimeException.class)
    public void failWhenPrivateKeyIsNeitherSenderOrReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest = new SecuredWithNemKeysPrivacyStrategy(TEST_UNKNOWN_KEY, TEST_PUBLIC_KEY);

        unitUnderTest.decrypt(SAMPLE_DATA, aSampleTransactionWithPrivateKeyAsSender(), aSampleResourceHashMessage());
    }

    private byte[] encrypt(byte[] data, KeyPair sender, KeyPair recepient) {
        return engine.createBlockCipher(sender, recepient).encrypt(data);
    }

    private TransferTransaction aSampleTransactionWithPrivateKeyAsSender() {
        return new TransferTransaction(null, new Account(KEYPAIR_FROM_PRIVATE_KEY), new Account(KEYPAIR_FROM_PUBLIC_KEY), null, null);
   }

    private TransferTransaction aSampleTransactionWithPrivateKeyAsReceiver() {
        return new TransferTransaction(null, new Account(KEYPAIR_FROM_PUBLIC_KEY), new Account(KEYPAIR_FROM_PRIVATE_KEY), null, null);
    }

    private ResourceHashMessage aSampleResourceHashMessage() {
        return new ResourceHashMessage();
    }
}
