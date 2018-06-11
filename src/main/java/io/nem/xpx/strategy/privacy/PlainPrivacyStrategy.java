package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;


/**
 * The Class PlainPrivacyStrategy.
 */
public class PlainPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#encrypt(byte[])
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        return data;
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#decrypt(byte[], org.nem.core.model.TransferTransaction, io.nem.xpx.service.model.buffers.ResourceHashMessage)
     */
    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        return data;
    }

}
