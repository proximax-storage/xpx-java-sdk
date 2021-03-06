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

package io.proximax.xpx.service;

import io.proximax.xpx.builder.TransferTransactionBuilder;
import io.proximax.xpx.exceptions.AnnounceTransactionFailureException;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;

import static java.lang.String.format;


/**
 * The Class TransactionAnnouncer.
 */
public class TransactionAnnouncer {

    /** The transaction fee calculators. */
    private final TransactionFeeCalculators transactionFeeCalculators;
    
    /** The transaction sender. */
    private final TransactionSender transactionSender;

    /**
     * Instantiates a new transaction announcer.
     *
     * @param transactionFeeCalculators the transaction fee calculators
     * @param transactionSender the transaction sender
     */
    public TransactionAnnouncer(TransactionFeeCalculators transactionFeeCalculators, TransactionSender transactionSender) {
        this.transactionFeeCalculators = transactionFeeCalculators;
        this.transactionSender = transactionSender;
    }

    /**
     * Announce transaction for uploaded content.
     *
     * @param nemMessage the proximax message
     * @param senderPrivateKey the sender private key
     * @param receiverPublicKey the receiver public key
     * @param mosaics the mosaics
     * @return the string
     * @throws Exception the exception
     */
    public String announceTransactionForUploadedContent(Message nemMessage, String senderPrivateKey, String receiverPublicKey,
                                                        Mosaic[] mosaics) throws Exception {

		Mosaic xpxMosaic = new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(1));
		
        final TransferTransaction transferTransaction = new TransferTransactionBuilder(transactionFeeCalculators)
                .sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
                .recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(receiverPublicKey))))
                .version(2)
                .amount(Amount.fromNem(1l))
                .message(nemMessage)
                .addMosaics(mosaics)
                .addMosaic(xpxMosaic) // XPX Mosaic
                .buildAndSignTransaction();

        final NemAnnounceResult announceResult = transactionSender.sendTransferTransaction(transferTransaction);

        if (announceResult.getCode() == NemAnnounceResult.CODE_SUCCESS)
            return announceResult.getTransactionHash().toString();
        else
            throw new AnnounceTransactionFailureException(
                    format("Announcement of Nem transaction failed: %s", announceResult.getMessage()));
    }
}
