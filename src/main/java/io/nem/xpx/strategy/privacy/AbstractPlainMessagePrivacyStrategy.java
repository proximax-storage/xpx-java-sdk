package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecodeNemMessageFailureException;
import io.nem.xpx.model.NemMessageType;
import org.nem.core.messages.PlainMessage;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;


/**
 * The Class AbstractPlainMessagePrivacyStrategy.
 */
public abstract class AbstractPlainMessagePrivacyStrategy extends PrivacyStrategy {

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#getNemMessageType()
     */
    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.PLAIN;
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#encodeToMessage(byte[])
     */
    @Override
    public Message encodeToMessage(byte[] data) {
        return new PlainMessage(data);
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#decodeTransaction(org.nem.core.model.TransferTransaction)
     */
    @Override
    public byte[] decodeTransaction(TransferTransaction transaction) {
        if (transaction.getMessage().getType() == MessageTypes.SECURE)
            throw new DecodeNemMessageFailureException("Unable to decode secure message with plain privacy strategy");
        return transaction.getMessage().getDecodedPayload();
    }
}
