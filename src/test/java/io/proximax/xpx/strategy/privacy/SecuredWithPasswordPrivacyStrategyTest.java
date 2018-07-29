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

import io.proximax.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;
import io.proximax.xpx.exceptions.EncryptionFailureException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


/**
 * The Class SecuredWithPasswordPrivacyStrategyTest.
 */
public class SecuredWithPasswordPrivacyStrategyTest {

    /** The Constant SAMPLE_DATA. */
    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    
    /** The Constant PASSWORD. */
    public static final String PASSWORD = "lkNzBmYmYyNTExZjZmNDYyZTdjYWJmNmY1MjJiYjFmZTk3Zjg2NDA5ZDlhOD";
    
    /** The Constant PASSWORD_TOO_SHORT. */
    public static final String PASSWORD_TOO_SHORT = "too short for a password";

    /** The encryptor. */
    @Mock
    private BinaryPBKDF2CipherEncryption encryptor;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Fail init without password.
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInitWithoutPassword() {
        new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), null);
    }

    /**
     * Fail init with password not meeting minimum length.
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInitWithPasswordNotMeetingMinimumLength() {
        new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD_TOO_SHORT);
    }

    /**
     * Fail on exception while encrypting.
     *
     * @throws Exception the exception
     */
    @Test(expected = EncryptionFailureException.class)
    public void failOnExceptionWhileEncrypting() throws Exception {
        given(encryptor.encrypt(any(byte[].class), any(char[].class))).willThrow(new RuntimeException("failed encryption"));

        final SecuredWithPasswordPrivacyStrategy unitUnderTest = new SecuredWithPasswordPrivacyStrategy(encryptor, PASSWORD);
        unitUnderTest.encrypt(SAMPLE_DATA);
    }

    /**
     * Return encrypted with password.
     */
    @Test
    public void returnEncryptedWithPassword() {
        final SecuredWithPasswordPrivacyStrategy unitUnderTest =
                new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD);

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    /**
     * Return decrypted with password.
     */
    @Test
    public void returnDecryptedWithPassword() {
        final SecuredWithPasswordPrivacyStrategy unitUnderTest =
                new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), PASSWORD);
        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        final byte[] decrypted = unitUnderTest.decrypt(encrypted, null, null);

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

}
