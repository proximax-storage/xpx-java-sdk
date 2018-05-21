package io.nem.xpx.strategy.privacy;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;

public abstract class PrivacyStrategy {

    public abstract NemMessageType getNemMessageType();

    public abstract byte[] encrypt(final byte[] data);
    public abstract byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage);

    public abstract Message encodeToMessage(final byte[] payload);
    public abstract byte[] decodeTransaction(final TransferTransaction transaction);

}
