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

import io.proximax.xpx.exceptions.DecodeNemMessageFailureException;
import io.proximax.xpx.model.NemMessageType;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;

import static io.proximax.xpx.utils.ParameterValidationUtils.checkParameter;


/**
 * The Class AbstractSecureMessagePrivacyStrategy.
 */
public abstract class AbstractSecureMessagePrivacyStrategy extends PrivacyStrategy {

    /** The key pair of private key. */
    public final KeyPair keyPairOfPrivateKey;
    
    /** The key pair of public key. */
    public final KeyPair keyPairOfPublicKey;
    
    /** The account with private key. */
    public final Account accountWithPrivateKey;
    
    /** The account with public key. */
    public final Account accountWithPublicKey;

    /**
     * Instantiates a new abstract secure message privacy strategy.
     *
     * @param privateKey the private key
     * @param publicKey the public key
     */
    public AbstractSecureMessagePrivacyStrategy(String privateKey, String publicKey) {
        checkParameter(privateKey != null, "private key is required");
        checkParameter(publicKey != null, "public key is required");

        this.keyPairOfPrivateKey = new KeyPair(PrivateKey.fromHexString(privateKey));
        this.keyPairOfPublicKey = new KeyPair(PublicKey.fromHexString(publicKey));
        this.accountWithPrivateKey = new Account(keyPairOfPrivateKey);
        this.accountWithPublicKey = new Account(keyPairOfPublicKey);
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#getNemMessageType()
     */
    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.SECURE;
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#encodeToMessage(byte[])
     */
    @Override
    public Message encodeToMessage(byte[] data) {
        return SecureMessage.fromDecodedPayload(accountWithPrivateKey, accountWithPublicKey, data);
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#decodeTransaction(org.proximax.core.model.TransferTransaction)
     */
    @Override
    public byte[] decodeTransaction(TransferTransaction transaction) {
        if (transaction.getMessage().getType() == MessageTypes.PLAIN)
            return transaction.getMessage().getDecodedPayload();

        if (transaction.getSigner().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            return SecureMessage.fromEncodedPayload(accountWithPrivateKey, accountWithPublicKey,
                    transaction.getMessage().getEncodedPayload()).getDecodedPayload();

        } else if (transaction.getRecipient().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            return SecureMessage.fromEncodedPayload(accountWithPublicKey, accountWithPrivateKey,
                    transaction.getMessage().getEncodedPayload()).getDecodedPayload();

        } else {
            throw new DecodeNemMessageFailureException("Private key cannot be used to decode the Nem secured message.");
        }
    }
}
