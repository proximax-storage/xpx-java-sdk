/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.strategy.privacy;

import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;


/**
 * The Class PlainPrivacyStrategy.
 */
public class PlainPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#encrypt(byte[])
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        return data;
    }

    /* (non-Javadoc)
     * @see io.proximax.xpx.strategy.privacy.PrivacyStrategy#decrypt(byte[], org.proximax.core.model.TransferTransaction, io.proximax.xpx.service.model.buffers.ResourceHashMessage)
     */
    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        return data;
    }

}
