package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

public abstract class AbstractPrivacyStrategy {
    public abstract byte[] encrypt(final byte[] data);
    public abstract byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage);
}
