package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecryptionFailureException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.model.TransferTransaction;

public final class SecuredWithNemKeysPrivacyStrategy extends AbstractSecureMessagePrivacyStrategy {


    public SecuredWithNemKeysPrivacyStrategy(String privateKey, String publicKey) {
        super(privateKey, publicKey);
    }

    @Override
    public byte[] encrypt(final byte[] data) {
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPrivateKey, keyPairOfPublicKey).encrypt(data);
    }

    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {

        if (!transaction.getSigner().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded()) &&
                !transaction.getRecipient().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            throw new DecryptionFailureException("Decrypt of data is unsuccessful");
        }
        return CryptoEngines.defaultEngine().createBlockCipher(keyPairOfPublicKey, keyPairOfPrivateKey).decrypt(data);

    }
}
