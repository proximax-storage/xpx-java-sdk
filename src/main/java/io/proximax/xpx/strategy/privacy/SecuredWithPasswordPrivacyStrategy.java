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
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

import static io.proximax.xpx.utils.ParameterValidationUtils.checkParameter;


/**
 * The Class SecuredWithPasswordPrivacyStrategy.
 */
public final class SecuredWithPasswordPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    /** The Constant MINIMUM_PASSWORD_LENGTH. */
    private static final int MINIMUM_PASSWORD_LENGTH = 50;
    
    /** The encryptor. */
    private final BinaryPBKDF2CipherEncryption encryptor;
    
    /** The password. */
    private final char[] password;


    /**
     * Instantiates a new secured with password privacy strategy.
     *
     * @param encryptor the encryptor
     * @param password the password
     */
    public SecuredWithPasswordPrivacyStrategy(BinaryPBKDF2CipherEncryption encryptor, String password) {
        checkParameter(password != null, "password is required");
        checkParameter(password.length() >= MINIMUM_PASSWORD_LENGTH, "minimum length for password is 50");

        this.encryptor = encryptor;
        this.password = password.toCharArray();
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#encrypt(byte[])
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        try {
            return encryptor.encrypt(data, password);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered encrypting data", e);
        }
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#decrypt(byte[], org.proximax.core.model.TransferTransaction, io.proximax.xpx.service.model.buffers.ResourceHashMessage)
     */
    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        try {
            return encryptor.decrypt(data, password);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered decrypting data", e);
        }
    }
}
