package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecodeNemMessageFailureException;
import io.nem.xpx.model.NemMessageType;
import org.nem.core.messages.PlainMessage;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;

public abstract class AbstractPlainMessagePrivacyStrategy extends PrivacyStrategy {

    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.PLAIN;
    }

    @Override
    public Message encodeToMessage(byte[] data) {
        return new PlainMessage(data);
    }

    @Override
    public byte[] decodeTransaction(TransferTransaction transaction) {
        if (transaction.getMessage().getType() == MessageTypes.SECURE)
            throw new DecodeNemMessageFailureException("Unable to decode secure message with plain privacy strategy");
        return transaction.getMessage().getDecodedPayload();
    }
}
