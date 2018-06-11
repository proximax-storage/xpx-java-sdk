package io.nem.xpx.strategy.privacy;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;


/**
 * The Class PrivacyStrategy.
 */
public abstract class PrivacyStrategy {

    /**
     * Gets the nem message type.
     *
     * @return the nem message type
     */
    public abstract NemMessageType getNemMessageType();

    /**
     * Encrypt.
     *
     * @param data the data
     * @return the byte[]
     */
    public abstract byte[] encrypt(final byte[] data);
    
    /**
     * Decrypt.
     *
     * @param data the data
     * @param transaction the transaction
     * @param hashMessage the hash message
     * @return the byte[]
     */
    public abstract byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage);

    /**
     * Encode to message.
     *
     * @param payload the payload
     * @return the message
     */
    public abstract Message encodeToMessage(final byte[] payload);
    
    /**
     * Decode transaction.
     *
     * @param transaction the transaction
     * @return the byte[]
     */
    public abstract byte[] decodeTransaction(final TransferTransaction transaction);

}
