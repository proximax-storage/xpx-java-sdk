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

import java.util.Map;


/**
 * A factory for creating PrivacyStrategy objects.
 */
public class PrivacyStrategyFactory {

    /** The plain privacy strategy. */
    public static PrivacyStrategy plainPrivacyStrategy;

    /**
     * Instantiates a new privacy strategy factory.
     */
    private PrivacyStrategyFactory() {}

    /**
     * Plain privacy.
     *
     * @return the privacy strategy
     */
    public static PrivacyStrategy plainPrivacy() {
        if (plainPrivacyStrategy == null)
            plainPrivacyStrategy = new PlainPrivacyStrategy();
        return plainPrivacyStrategy;
    }

    /**
     * Secured with proximax keys privacy strategy.
     *
     * @param senderOrReceiverPrivateKey the sender or receiver private key
     * @param receiverOrSenderPublicKey the receiver or sender public key
     * @return the privacy strategy
     */
    public static PrivacyStrategy securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        return new SecuredWithNemKeysPrivacyStrategy(senderOrReceiverPrivateKey, receiverOrSenderPublicKey);
    }

    /**
     * Secured with password privacy strategy.
     *
     * @param password the password
     * @return the privacy strategy
     */
    public static PrivacyStrategy securedWithPasswordPrivacyStrategy(String password) {
        return new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), password);
    }

    public static PrivacyStrategy securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                                                Map<Integer, byte[]> secretParts) {
        return new SecuredWithShamirSecretSharingPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), secretTotalPartCount,
                secretMinimumPartCountToBuild, secretParts);
    }
}
