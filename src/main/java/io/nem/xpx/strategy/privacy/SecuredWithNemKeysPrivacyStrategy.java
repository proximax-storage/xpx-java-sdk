package io.nem.xpx.strategy.privacy;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.MessageUtils;
import org.nem.core.model.Address;
import org.nem.core.model.TransferTransaction;

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
        final Address nemAddress = MessageUtils.getNemAddressFromPrivateKey(senderOrReceiverPrivateKey);
        if (transaction.getSigner().getAddress().getEncoded().equals(nemAddress.toString())) {
            return MessageUtils.decryptDataWithSenderPrivateNemKey(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
        } else if (transaction.getRecipient().getAddress().getEncoded().equals(nemAddress.toString())) {
            return MessageUtils.decryptDataWithReceiverPrivateNemKey(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
        } else {
            throw new RuntimeException("Decrypt of data is unsuccessful");
        }
    }
}
