package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.MessageEncryptionUtils;
import org.nem.core.model.TransferTransaction;

import static java.lang.String.format;

public class SecuredWithNemKeysPrivacyStrategy extends AbstractPrivacyStrategy {

    private String senderOrReceiverPrivateKey;
    private String receiverOrSenderPublicKey;

    public SecuredWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        this.senderOrReceiverPrivateKey = senderOrReceiverPrivateKey;
        this.receiverOrSenderPublicKey = receiverOrSenderPublicKey;
    }

    public byte[] encrypt(final byte[] data) {
        return MessageEncryptionUtils.encryptWithNemKeys(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
    }

    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKey)) {
            return MessageEncryptionUtils.decryptWithNemKeys(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);

        } else if (transaction.getRecipient().getAddress().getEncoded().equals(senderOrReceiverPrivateKey)) {
            return MessageEncryptionUtils.decryptWithNemKeys(receiverOrSenderPublicKey, senderOrReceiverPrivateKey, data);

        } else {
            throw new RuntimeException(format("Decrypt of data is unsuccessful for %s", hashMessage.hash()));
        }
    }
}
