package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecryptionFailureException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.junit.Test;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;

import java.util.Arrays;

import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;


/**
 * The Class SecuredWithNemKeysPrivacyStrategyTest.
 */
public class SecuredWithNemKeysPrivacyStrategyTest {

    /** The Constant SAMPLE_DATA. */
    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    
    /** The Constant SENDER_KEYPAIR. */
    public static final KeyPair SENDER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));
    
    /** The Constant RECEIVER_KEYPAIR. */
    public static final KeyPair RECEIVER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_2));
    
    /** The Constant ANOTHER_KEYPAIR. */
    public static final KeyPair ANOTHER_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY_3));

    /**
     * Fail init without private key.
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPrivateKey() {
        new SecuredWithNemKeysPrivacyStrategy(null, RECEIVER_KEYPAIR.getPublicKey().toString());
    }

    /**
     * Fail init without public key.
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPublicKey() {
        new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), null);
    }

    /**
     * Return encrypted with keys.
     */
    @Test
    public void returnEncryptedWithKeys() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    /**
     * Fail decrypt when private key is neither sender or receiver.
     */
    @Test(expected = DecryptionFailureException.class)
    public void failDecryptWhenPrivateKeyIsNeitherSenderOrReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(ANOTHER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        unitUnderTest.decrypt(SAMPLE_DATA, aSampleTransaction(), aSampleResourceHashMessage());
    }

    /**
     * Return decrypted with keys where private key is sender.
     */
    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsSender() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(SENDER_KEYPAIR.getPrivateKey().toString(), RECEIVER_KEYPAIR.getPublicKey().toString());

        final byte[] decrypted = unitUnderTest.decrypt(sampleEncryptedData(), aSampleTransaction(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    /**
     * Return decrypted with keys where private key is receiver.
     */
    @Test
    public void returnDecryptedWithKeysWherePrivateKeyIsReceiver() {
        final SecuredWithNemKeysPrivacyStrategy unitUnderTest =
                new SecuredWithNemKeysPrivacyStrategy(RECEIVER_KEYPAIR.getPrivateKey().toString(), SENDER_KEYPAIR.getPublicKey().toString());

        final byte[] decrypted = unitUnderTest.decrypt(sampleEncryptedData(), aSampleTransaction(), aSampleResourceHashMessage());

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    /**
     * Sample encrypted data.
     *
     * @return the byte[]
     */
    private byte[] sampleEncryptedData() {
        return CryptoEngines.defaultEngine().createBlockCipher(
                new KeyPair(SENDER_KEYPAIR.getPrivateKey()),
                new KeyPair(RECEIVER_KEYPAIR.getPublicKey())).encrypt(SAMPLE_DATA);
    }

    /**
     * A sample transaction.
     *
     * @return the transfer transaction
     */
    private TransferTransaction aSampleTransaction() {
        return new TransferTransaction(null, new Account(SENDER_KEYPAIR), new Account(RECEIVER_KEYPAIR), null, null);
   }

    /**
     * A sample resource hash message.
     *
     * @return the resource hash message
     */
    private ResourceHashMessage aSampleResourceHashMessage() {
        return new ResourceHashMessage();
    }
}
