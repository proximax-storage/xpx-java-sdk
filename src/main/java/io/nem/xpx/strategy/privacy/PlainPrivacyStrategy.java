package io.nem.xpx.strategy.privacy;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

public class PlainPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    @Override
    public byte[] encrypt(final byte[] data) {
        return data;
    }

    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        return data;
    }

}
