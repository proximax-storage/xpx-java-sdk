package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecryptionFailureException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.model.TransferTransaction;


/**
 * The Class SecuredWithNemKeysPrivacyStrategy.
 */
public final class SecuredWithSenderNemKeysPrivacyStrategy extends AbstractSecureMessagePrivacyStrategy {


    /**
     * Instantiates a new secured with nem keys privacy strategy.
     *
     * @param senderPrivateKey the private key
     * @param senderPublicKey the public key
     */
    public SecuredWithSenderNemKeysPrivacyStrategy(String senderPrivateKey, String senderPublicKey) {
        super(senderPrivateKey, senderPublicKey);
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#encrypt(byte[])
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPrivateKey, keyPairOfPrivateKey).encrypt(data);
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#decrypt(byte[], org.nem.core.model.TransferTransaction, io.nem.xpx.service.model.buffers.ResourceHashMessage)
     */
    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {

        if (transaction != null && !transaction.getSigner().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            throw new DecryptionFailureException("Decrypt of data is unsuccessful");
        }
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPrivateKey, keyPairOfPrivateKey).decrypt(data);

    }
}
