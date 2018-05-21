package io.nem.xpx.strategy.privacy;

import io.nem.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;
import io.nem.xpx.exceptions.EncryptionFailureException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class SecuredWithPasswordPrivacyStrategyTest {

    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    public static final String PASSWORD = "lkNzBmYmYyNTExZjZmNDYyZTdjYWJmNmY1MjJiYjFmZTk3Zjg2NDA5ZDlhOD";
    public static final String PASSWORD_TOO_SHORT = "too short for a password";

    @Mock
    private BinaryPBKDF2CipherEncryption encryptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPassword() {
        new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failInitWithPasswordNotMeetingMinimumLength() {
        new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD_TOO_SHORT);
    }

    @Test(expected = EncryptionFailureException.class)
    public void failOnExceptionWhileEncrypting() throws Exception {
        given(encryptor.encrypt(any(byte[].class), any(char[].class))).willThrow(new RuntimeException("failed encryption"));

        final SecuredWithPasswordPrivacyStrategy unitUnderTest = new SecuredWithPasswordPrivacyStrategy(encryptor, PASSWORD);
        unitUnderTest.encrypt(SAMPLE_DATA);
    }

    @Test
    public void returnEncryptedWithPassword() {
        final SecuredWithPasswordPrivacyStrategy unitUnderTest =
                new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD);

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    @Test
    public void returnDecryptedWithPassword() {
        final SecuredWithPasswordPrivacyStrategy unitUnderTest =
                new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD);
        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        final byte[] decrypted = unitUnderTest.decrypt(encrypted, null, null);

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

}
