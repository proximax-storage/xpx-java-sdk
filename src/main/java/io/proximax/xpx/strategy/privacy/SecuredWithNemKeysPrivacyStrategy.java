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

import io.proximax.xpx.exceptions.DecryptionFailureException;
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.model.TransferTransaction;


/**
 * The Class SecuredWithNemKeysPrivacyStrategy.
 */
public final class SecuredWithNemKeysPrivacyStrategy extends AbstractSecureMessagePrivacyStrategy {


    /**
     * Instantiates a new secured with proximax keys privacy strategy.
     *
     * @param privateKey the private key
     * @param publicKey the public key
     */
    public SecuredWithNemKeysPrivacyStrategy(String privateKey, String publicKey) {
        super(privateKey, publicKey);
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#encrypt(byte[])
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPrivateKey, keyPairOfPublicKey).encrypt(data);
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#decrypt(byte[], org.proximax.core.model.TransferTransaction, io.proximax.xpx.service.model.buffers.ResourceHashMessage)
     */
    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {

        if (transaction != null && !transaction.getSigner().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded()) &&
                !transaction.getRecipient().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            throw new DecryptionFailureException("Decrypt of data is unsuccessful");
        }
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPublicKey, keyPairOfPrivateKey).decrypt(data);

    }
}
