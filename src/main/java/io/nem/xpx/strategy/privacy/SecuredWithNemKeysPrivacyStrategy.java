package io.nem.xpx.strategy.privacy;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.MessageUtils;
import org.nem.core.model.TransferTransaction;

import static java.lang.String.format;

public final class SecuredWithNemKeysPrivacyStrategy extends PrivacyStrategy {

    private String senderOrReceiverPrivateKey;
    private String receiverOrSenderPublicKey;

    public SecuredWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        super(NemMessageType.SECURE);
        this.senderOrReceiverPrivateKey = senderOrReceiverPrivateKey;
        this.receiverOrSenderPublicKey = receiverOrSenderPublicKey;
    }

    @Override
    public byte[] encrypt(final byte[] data) {
        return MessageUtils.encryptDataWithNemKeys(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
    }

    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKey)) {
            return MessageUtils.decryptDataWithSenderPrivateNemKey(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
        } else if (transaction.getRecipient().getAddress().getEncoded().equals(senderOrReceiverPrivateKey)) {
            return MessageUtils.decryptDataWithReceiverPrivateNemKey(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
        } else {
            throw new RuntimeException(format("Decrypt of data is unsuccessful for %s", hashMessage.hash()));
        }
    }
}
